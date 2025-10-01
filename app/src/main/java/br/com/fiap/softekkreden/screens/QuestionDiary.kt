import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PagAvaliacao(navController: NavController) {
    var selected by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(br.com.fiap.softekkreden.ui.theme.Purple80),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = "Como você está hoje?",
                fontSize = 28.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        val estados = listOf(
            "Animado", "Cansado",
            "Motivado", "Estressado",
            "Preocupado", "Satisfeito"
        )

        estados.chunked(2).forEach { linha ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                linha.forEach { estado ->
                    EstadoButton(
                        texto = estado,
                        isSelected = selected == estado,
                        onClick = {
                            selected = estado
                            navController.navigate("questionScreen")
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun EstadoButton(
    texto: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(targetValue = if (isSelected) 1.1f else 1f)
    val shadowColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(width = 150.dp, height = 50.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .shadow(8.dp, shape = CircleShape, ambientColor = shadowColor)
            .border(2.dp, shadowColor, shape = CircleShape),
        shape = CircleShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        colors = ButtonDefaults.buttonColors(br.com.fiap.softekkreden.ui.theme.PurpleGrey40)
    ) {
        Text(text = texto)
    }
}