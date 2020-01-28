package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
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

        val observer = Observer<Event<Unit>>{}
        try {
            // Then the new task event is triggered]
            taskViewModel.newTaskEvent.observeForever(observer)

            taskViewModel.addNewTask()

            val value = taskViewModel.newTaskEvent.value

            assertThat(value?.getContentIfNotHandled(),(not(nullValue())))
        }catch (e:Exception){

        }finally {
            taskViewModel.newTaskEvent.removeObserver(observer)
        }
    }
}