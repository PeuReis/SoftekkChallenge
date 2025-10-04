import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.softekkreden.utils.GuidManager
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Questions(navController: NavController,viewModel: QuestionsViewModel = viewModel()) {
    val questions by viewModel.questions.observeAsState(emptyList())
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.carregarQuestions()
    }

    if (questions.isNotEmpty()) {
        val question = questions[currentIndex]
        val dataAtual = YearMonth.now()
        val formatador = DateTimeFormatter.ofPattern("MM/yyyy")
        val dataFormatada = dataAtual.format(formatador)
        val context = LocalContext.current
        val guid = GuidManager.getOrCreateGuid(context)

        PerguntaComOpcoesAnimada(
            pergunta = question.text,
            opcoes = question.alternatives,
            onRespostaSelecionada = { resposta ->
                viewModel.enviarResposta(guid,Answer( questionId = question.id, selectedAlternative = resposta, date = dataFormatada))
                if (currentIndex < questions.lastIndex) {
                    currentIndex++
                } else {
                    // se acabou, pode navegar para outra tela, mostrar msg final, etc
                    navController.navigate("home")
                }
            }
        )
    } else {
        Text("Carregando perguntas...")
    }

}


@Composable
fun PerguntaComOpcoesAnimada(
    pergunta: String,
    opcoes: List<String>,
    onRespostaSelecionada: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pergunta,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        opcoes.forEach { opcao ->
            BotaoResposta(opcao) {
                onRespostaSelecionada(opcao)
            }
        }
    }
}
@Composable
fun BotaoResposta(texto: String, aoClicar: () -> Unit) {
    val isPressed = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed.value) 1.05f else 1f)

    Button(
        onClick = {
            isPressed.value = true
            aoClicar()
        },
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .size(width = 240.dp, height = 56.dp)
            .shadow(8.dp, shape = CircleShape)
            .border(2.dp, Color.Black, shape = CircleShape),
        shape = CircleShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        colors = ButtonDefaults.buttonColors(br.com.fiap.softekkreden.ui.theme.PurpleGrey40)
    ) {
        Text(text = texto)
    }
}

