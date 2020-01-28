package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent(){
        // Given a fresh TasksViewModel
       val taskViewModel =  TasksViewModel(ApplicationProvider.getApplicationContext())

            taskViewModel.addNewTask()
            // Then the new task event is triggered
            val value = taskViewModel.newTaskEvent.getOrAwaitValue()

            assertThat(value?.getContentIfNotHandled(),(not(nullValue())))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){
        val taskViewModel =  TasksViewModel(ApplicationProvider.getApplicationContext())
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = taskViewModel.tasksAddViewVisible.getOrAwaitValue()

        assertThat(value,`is`(true))

    }

}