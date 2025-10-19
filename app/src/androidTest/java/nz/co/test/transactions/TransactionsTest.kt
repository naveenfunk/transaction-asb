package nz.co.test.transactions

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Rule

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class TransactionsTest {

    //TODO I want to incliude UI tests but couldn't. A basic test of navigating to detail screen once a transaction item is clicked.
    @get:Rule val composeTestRule = createComposeRule()

}