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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{

    // Subject under test
    private lateinit var tasksViewModel: TasksViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }


    @Test
    fun addNewTask_setsNewTaskEvent(){

        tasksViewModel.addNewTask()
            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.getOrAwaitValue()

            assertThat(value?.getContentIfNotHandled(),(not(nullValue())))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){

        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()

        assertThat(value,`is`(true))

    }

}