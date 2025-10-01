import android.R.attr.scaleX
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.softekkreden.R

@Composable
fun EmoteButtonAnimated(
    titulo: String,
    iconeId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(targetValue = if (isSelected) 1.3f else 1f)
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Card(
            shape = CircleShape,
            border = BorderStroke(2.dp, borderColor),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .size(64.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            Icon(
                painter = painterResource(id = iconeId),
                contentDescription = titulo,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize(),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = titulo,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
@Composable
fun PagAcompanhamento(navController: NavController) {
    var selectedMood by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Como você está se sentindo hoje?",
            fontSize = 26.sp,
            modifier = Modifier.padding(32.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        val emotes = listOf(
            "Triste" to R.drawable.triste,
            "Feliz" to R.drawable.feliz,
            "Cansado" to R.drawable.cansado,
            "Ansioso" to R.drawable.ansioso,
            "Medo" to R.drawable.medo,
            "Raiva" to R.drawable.nervoso
        )

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            emotes.chunked(3).forEach { linha ->
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    linha.forEach { (titulo, iconeId) ->
                        EmoteButtonAnimated(
                            titulo = titulo,
                            iconeId = iconeId,
                            isSelected = selectedMood == titulo
                        ) {
                            selectedMood = titulo
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedVisibility(visible = selectedMood != null) {
            Button(
                onClick = { navController.navigate("questionDiaryScreen") },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Confirmar: ${selectedMood ?: ""}")
            }
        }
    }
}