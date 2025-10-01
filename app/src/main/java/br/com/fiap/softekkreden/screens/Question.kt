import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Pergunta(
    val texto: String,
    val opcoes: List<String>
)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Questionario(
    perguntas: List<Pergunta>,
    onFinalizado: (respostas: List<String>) -> Unit
) {
    var perguntaAtualIndex by remember { mutableIntStateOf(0) }
    val respostas = remember { mutableStateListOf<String>() }

    AnimatedContent(
        targetState = perguntaAtualIndex,
        transitionSpec = {
            slideInVertically { it } + fadeIn() with
                    slideOutVertically { -it } + fadeOut()
        },
        label = "PerguntaAnimada"
    ) { index ->
        if (index < perguntas.size) {
            val pergunta = perguntas[index]
            PerguntaComOpcoesAnimada(
                pergunta = pergunta.texto,
                opcoes = pergunta.opcoes,
                onRespostaSelecionada = { resposta ->
                    respostas.add(resposta)
                    if (index < perguntas.size - 1) {
                        perguntaAtualIndex++
                    } else {
                        onFinalizado(respostas.toList())
                    }
                }
            )
        }
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
@SuppressLint("SuspiciousIndentation")
@Composable
fun PagQuestionario(navController: NavController) {
    val listaPerguntas = listOf(
        Pergunta("Como voce avalia a sua carga de trabalho?", listOf("Muito Leve", "Leve", "média", "Alta", "Muito Alta")),
        Pergunta("Sua carga de trabalho afeta sua qualidade de vida?", listOf("Não", "Raramente", "Às Vezes", "Frequentemente", "Sempre")),
        Pergunta("Você trabalha além do seu horário regular?", listOf("Não", "Raramente", "Às Vezes", "Frequentemente", "Sempre")),
        Pergunta("Você tem apresentado sintomas como insônia, irritabilidade ou cansaço extremo?", listOf("Nunca", "Raramente", "Às Vezes", "Frequentemente", "Sempre")),
        Pergunta("Você sente que sua saúde mental prejudica sua produtividade no trabalho?", listOf("Nunca", "Raramente", "Às Vezes", "Frequentemente", "Sempre")),
        Pergunta("Como está o seu relacionamento com seu chefe numa escala de 1 a 5? (Sendo 01 - ruim e 05 - Ótimo)", listOf("1", "2", "3", "4", "5")),
        Pergunta("Como está o seu relacionamento com seus colegas de trabalho numa escala de 1 a 5? (Sendo 01 - ruim e 05 - Ótimo)", listOf("1", "2", "3", "4", "5")),
        Pergunta("Sinto que sou tratado(a) com respeito pelos meus colegas de trabalho.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Consigo me relacionar de forma saudável e colaborativa com minha equipe.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Tenho liberdade para expressar minhas opiniões sem medo de retaliações.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Me sinto acolhido(a) a parte do time onde trabalho.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Sinto que existe espírito de cooperação entre os colaboradores.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Recebo orientações claras e objetivas sobre minhas atividades e responsabilidades.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Sinto que posso me comunicar abertamente com minha liderança.", listOf("1", "2", "3", "4", "5")),
        Pergunta("As informações importantes circulam de forma eficiente dentro da empresa.", listOf("1", "2", "3", "4", "5")),
        Pergunta(" Tenho clareza sobre as metas e os resultados esperados de mim.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Minha liderança demonstra interesse pelo meu bem-estar no trabalho", listOf("1", "2", "3", "4", "5")),
        Pergunta("Minha liderança está disponível para me ouvir quando necessário.", listOf("1", "2", "3", "4", "5")),
        Pergunta("Me sinto confortável para reportar problemas ou dificuldades ao meu líder", listOf("1", "2", "3", "4", "5")),
        Pergunta("Minha liderança reconhece minhas entregas e esforços", listOf("1", "2", "3", "4", "5")),
        Pergunta("Existe confiança e transparência na relação com minha liderança", listOf("1", "2", "3", "4", "5")),

        )

        Questionario(perguntas = listaPerguntas) { respostas ->
            navController.navigate("dashboards")
        }
}

