import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun CharacterCard(character: DisneyCharacter) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            // Imagen del personaje con bordes redondeados y grande
            Image(
                painter = rememberAsyncImagePainter(model = character.imageUrl),
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(250.dp) // Cambia el tamaño de la imagen a más grande
                    .clip(CircleShape) // Ajusta el redondeado
                    .border(2.dp, Color.Cyan, RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el texto

            // Información del personaje
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Films: ${character.films.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCard() {
    // Ejemplo de un personaje ficticio para la vista previa
    val sampleCharacter = DisneyCharacter(
        _id = 1,
        films = listOf("Film 1", "Film 2"),
        shortFilms = listOf("Short 1"),
        tvShows = listOf("Show 1", "Show 2"),
        videoGames = listOf("Game 1"),
        parkAttractions = listOf("Attraction 1"),
        allies = listOf("Ally 1", "Ally 2"),
        enemies = listOf("Enemy 1", "Enemy 2"),
        name = "Mickey Mouse",
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png", // URL de prueba
        url = "https://disneyapi.dev/characters/1"
    )

    // Llamamos a la tarjeta de personaje con los datos de muestra
    CharacterCard(character = sampleCharacter)
}
