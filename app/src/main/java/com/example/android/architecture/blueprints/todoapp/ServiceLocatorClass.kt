package com.example.android.architecture.blueprints.todoapp

import android.content.Context
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.source.DefaultTasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource

// make it singleton
object ServiceLocatorClass {
    @Volatile
    var tasksRepository:TasksRepository? = null
    private var database: ToDoDatabase? = null

    fun provideTaskRepository(context: Context):TasksRepository{
        synchronized(this){
            return tasksRepository?:createTaskRepository(context)
        }
    }

    private fun createTaskRepository(context: Context):TasksRepository {
        val newRepo = DefaultTasksRepository(TasksRemoteDataSource, createTaskLocalDataSource(context))
        tasksRepository = newRepo
        return newRepo
    }

    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val database = database ?: createDataBase(context)
        return TasksLocalDataSource(database.taskDao())
    }

    private fun createDataBase(context: Context): ToDoDatabase {
        val result = Room.databaseBuilder(
                context.applicationContext,
                ToDoDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

}