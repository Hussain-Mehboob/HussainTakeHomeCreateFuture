package com.createfuture.takehome.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.createfuture.takehome.R
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
                .paint(
                    painterResource(id = R.drawable.img_characters),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            when (charactersState) {
                is CharactersState.Error -> ErrorDialog(errorMessage = charactersState.error) { /*Do Nothing*/ }
                is CharactersState.GetCharactersListSuccess -> CharacterListScreen(characters = charactersState.data)
                CharactersState.Init -> {}
                CharactersState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CharacterListScreen(characters: List<ApiCharacter>) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        SearchTextField(value = searchText) {
            searchText = it
        }

        Spacer(modifier = Modifier.height(14.dp))

        val filteredCharacters = characters.filter {
            /* this can be modified to start with method as well to search based on character's firstname only */
            it.name.contains(searchText, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            with(filteredCharacters) {
                items(this) { character ->
                    CharacterItem(character) { /*Do Nothing On Click*/ }
                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text("Search") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedPlaceholderColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
        )
    )
}

@Composable
fun CharacterItem(character: ApiCharacter, onItemClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Left Section
            Column(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = character.name,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                LabelValue(label = "Culture: ", value = character.culture)
                LabelValue(label = "Born: ", value = character.born)
                LabelValue(label = "Died: ", value = character.died)
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right Section
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(0.6f)) {
                Text(
                    text = "Seasons:",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Text(
                    text = character.seasons,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LabelValue(label: String, value: String) {
    Row {
        Text(
            text = label, color = Color.White, fontSize = 16.sp
        )
        Text(
            text = value,
            color = Color.Gray,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewCharacterItem() {
    val dummyCharacter = ApiCharacter("123", "Savings", "001", "USD", "2024-01-01")
    CharacterItem(character = dummyCharacter)
}