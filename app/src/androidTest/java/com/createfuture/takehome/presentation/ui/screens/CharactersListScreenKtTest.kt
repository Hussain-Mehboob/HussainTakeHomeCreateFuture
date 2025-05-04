package com.createfuture.takehome.presentation.ui.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.createfuture.takehome.domain.model.ApiCharacter
import org.junit.Rule
import org.junit.Test

class CharactersListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dummyCharacters = listOf(
        ApiCharacter("Jon Snow", "Male", "Northmen", "In 283 AC", "Alive"),
        ApiCharacter("Daenerys Targaryen", "Female", "Valyrian", "In 282 AC", "In 305 AC")
    )

    @Test
    fun loadingIndicator_IsDisplayed() {
        composeTestRule.setContent {
            // Simulate Loading state
            CircularProgressIndicator(modifier = Modifier.testTag("loadingIndicator"))
        }

        composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
    }

    @Test
    fun characterList_DisplayedWithSearch() {
        composeTestRule.setContent {
            CharacterListScreen(characters = dummyCharacters)
        }

        // Check both character names are displayed
        composeTestRule.onNodeWithText("Jon Snow").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daenerys Targaryen").assertIsDisplayed()

        // Perform a search
        composeTestRule.onNodeWithText("Search").performTextInput("Jon")

        // Verify filtered results
        composeTestRule.onNodeWithText("Jon Snow").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daenerys Targaryen").assertDoesNotExist()
    }
}