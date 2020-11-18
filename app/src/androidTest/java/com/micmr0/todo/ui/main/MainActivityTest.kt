package com.micmr0.todo.ui.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.micmr0.todo.Todo
import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Rule

class MainActivityTest {
    private val todo = Todo()

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onBackPressedTest() {
        activityRule.scenario.onActivity { activity ->
            activity.saveTodo(todo)
            Espresso.onView(
                CoreMatchers.allOf(
                    ViewMatchers.withText("Url to img"),
                    ViewMatchers.isDisplayed()
                )
            )
        }
    }

    @Test
    fun saveTodoTest() {
        activityRule.scenario.onActivity { activity ->
            activity.saveTodo(todo)
            Espresso.onView(
                CoreMatchers.allOf(
                    ViewMatchers.withText("Save"),
                    ViewMatchers.isDisplayed()
                )
            )
        }
    }
}