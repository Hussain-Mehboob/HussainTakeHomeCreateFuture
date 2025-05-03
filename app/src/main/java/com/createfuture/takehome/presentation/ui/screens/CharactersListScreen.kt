package com.createfuture.takehome.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.createfuture.takehome.domain.model.ApiCharacter
import com.createfuture.takehome.presentation.common.intent.CharactersIntent
import com.createfuture.takehome.presentation.common.state.CharactersState
import com.createfuture.takehome.presentation.ui.common.ErrorDialog
import com.createfuture.takehome.presentation.viewmodel.HomeViewModel

@Composable
fun CharactersListScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val charactersState = viewModel.characters.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(CharactersIntent.GetCharactersList)
    }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (charactersState) {
                is CharactersState.Error -> ErrorDialog(errorMessage = charactersState.error) { /*Do Nothing*/ }
                is CharactersState.GetCharactersListSuccess -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {
                        with(charactersState.data) {
                            items(this) { character ->
                                CharacterItem(character) {
                                    //Do Nothing
                                }
                            }
                        }
                    }
                }

                CharactersState.Init -> {}
                CharactersState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CharacterItem(character: ApiCharacter, onItemClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = onItemClick
    ) {
        Row {
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = character.name, color = Color.White, fontSize = 16.sp)
                Row {
                    Text(text = "Culture: ", color = Color.White, fontSize = 16.sp)
                    Text(
                        text = character.culture, color = Color.White, fontSize = 16.sp
                    )
                }
                Row {
                    Text("Born: ", color = Color.White, fontSize = 16.sp)
                    Text(
                        text = character.born, color = Color.White, fontSize = 16.sp
                    )
                }
                Row {
                    Text(text = "Died: ", color = Color.White, fontSize = 16.sp)
                    Text(
                        text = character.died, color = Color.White, fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text("Seasons: ", color = Color.White, fontSize = 14.sp)
                var seasons = character.tvSeries.joinToString {
                    when (it) {
                        "Season 1" -> "I "
                        "Season 2" -> "II, "
                        "Season 3" -> "III, "
                        "Season 4" -> "IV, "
                        "Season 5" -> "V, "
                        "Season 6" -> "VI, "
                        "Season 7" -> "VII, "
                        "Season 8" -> "VIII"
                        else -> ""
                    }
                }
                Text(seasons, color = Color.White, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.size(18.dp))/* Column(
             modifier = Modifier
                 .fillMaxSize()
                 .paint(
                     painterResource(id = R.drawable.img_characters),
                     contentScale = ContentScale.FillBounds
                 )
                 .verticalScroll(rememberScrollState())
         ) {
             if (charactersBody.value != null) {
                 for (character in charactersBody.value!!) {

                 }
             }
         }*/
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterItem() {
    val dummyCharacter = ApiCharacter("123", "Savings", "001", "USD", "2024-01-01")
    CharacterItem(character = dummyCharacter)
}