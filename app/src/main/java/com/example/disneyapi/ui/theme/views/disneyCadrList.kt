package com.example.disneyapi.ui.theme.views
import CharacterCard
import DisneyCharacter
import ErrorScreen
import LoadingScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.disneyapi.viewmodel.DisneyUiState
import com.example.disneyapi.viewmodel.DisneyViewModel
import java.lang.reflect.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ImageGalleryScreen(viewModel: DisneyViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
        when (uiState) {
            is DisneyUiState.Loading -> LoadingScreen()
            is DisneyUiState.Success -> {
                val characters = (uiState as DisneyUiState.Success).characters.take(20)
                LazyColumn {
                    items(characters) { character ->
                        CharacterCard(character)
                    }
                }
            }
            is DisneyUiState.Error -> {
                ErrorScreen(message = (uiState as DisneyUiState.Error).message)
            }
        }
    }
}
