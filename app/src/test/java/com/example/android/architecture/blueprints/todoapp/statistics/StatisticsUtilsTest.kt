package com.example.android.architecture.blueprints.todoapp.statistics

import android.util.Log
import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){
        // GIVEN
        val tasks = listOf<Task>(Task("title1","Description1",false))
        // WHEN
        val result = getActiveAndCompletedStats(tasks)
        // THEN
        assertEquals(0f,result.completedTasksPercent)
        assertEquals(100f,result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_2Completed_3Active(){
        val tasks = listOf<Task>(
                Task("title1","Description1",true),
                Task("title1","Description1",true),
                Task("title1","Description1",false),
                Task("title1","Description1",false),
                Task("title1","Description1",false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(40f,result.completedTasksPercent)
        assertEquals(60f,result.activeTasksPercent)
    }


    @Test
    fun getActiveAndCompletedStats_Empty(){
        val tasks = emptyList<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f,result.completedTasksPercent)
        assertEquals(0f,result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_Null(){
        val tasks = null
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f,result.completedTasksPercent)
        assertEquals(0f,result.activeTasksPercent)
    }

}