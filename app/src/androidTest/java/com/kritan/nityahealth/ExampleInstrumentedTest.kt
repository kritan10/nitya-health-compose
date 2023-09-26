package com.kritan.nityahealth

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kritan.nityahealth", appContext.packageName)
    }

//    @Test
//    fun enableNextButtonOnlyWhenAtLeastOneOptionIsClicked() {
//        composeTestRule.setContent {
//            FoodGoalScreen(viewModel = viewModel, navigateUp = { }, navigateToFoodUserDetail = {})
//        }
//        composeTestRule.onNodeWithText("Next").assertIsNotEnabled()
//        Thread.sleep(2000)
//        composeTestRule.onNodeWithText("Gain Weight").performClick()
//        Thread.sleep(2000)
//        composeTestRule.onNodeWithText("Next").assertIsEnabled()
//        Thread.sleep(2000)
//    }

}