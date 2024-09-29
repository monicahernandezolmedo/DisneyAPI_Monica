import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.disneyapi.R
import com.example.disneyapi.ui.theme.views.ImageGalleryScreen
import com.example.disneyapi.viewmodel.DisneyUiState
import com.example.disneyapi.viewmodel.DisneyViewModel
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun HomeScreen(viewModel: DisneyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    // Llama a la función para obtener personajes solo si el estado es Loading
    LaunchedEffect(Unit) {
        if (uiState is DisneyUiState.Loading) {
            viewModel.getAllCharacters()
        }
    }

    when (uiState) {
        is DisneyUiState.Loading -> LoadingScreen()
        is DisneyUiState.Success -> {
            ImageGalleryScreen(viewModel)
        }
        is DisneyUiState.Error -> {
            ErrorScreen(message = (uiState as DisneyUiState.Error).message)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loader), // Cambia por tu imagen de carga
            contentDescription = "Loading"
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, message: String) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.error_load), // Tu imagen de error
                contentDescription = "Error",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre imagen y texto
            Text(
                text = "Falla", // Mensaje de error personalizado
                color = Color.Red,
                fontSize = 150.sp // Ajusta el tamaño del texto
            )
        }
    }
}
