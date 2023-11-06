package com.example.ecommerceapp.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.ecommerceapp.model.Handphone
import com.example.ecommerceapp.model.OrderHandphone
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.ecommerceapp.R
import com.example.ecommerceapp.onNodeWithStringId

class DetailScreenKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderHandphone = OrderHandphone(
        handphone = Handphone(4, "https://m.media-amazon.com/images/I/61-r+Yodx9L.jpg", "Jaket Hoodie Dicoding", 7500, "HP TERBAIK"),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EcommerceAppTheme {
                DetailContent(
                    fakeOrderHandphone.handphone.image,
                    fakeOrderHandphone.handphone.name,
                    fakeOrderHandphone.handphone.price,
                    fakeOrderHandphone.handphone.description,
                    fakeOrderHandphone.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderHandphone.handphone.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.price,
                fakeOrderHandphone.handphone.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}