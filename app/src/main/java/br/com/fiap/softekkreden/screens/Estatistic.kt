import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.softekkreden.model.AnalysisViewModel
import br.com.fiap.softekkreden.utils.GuidManager

@Composable
fun Estatistic(navController: NavController,viewModel: AnalysisViewModel = viewModel()) {
    val analysisList by viewModel.analysisList.observeAsState(emptyList())
    val context = LocalContext.current
    val guid = GuidManager.getOrCreateGuid(context)
    LaunchedEffect(Unit) {
        viewModel.carregarAnalises(guid,2025, 10)
    }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(analysisList) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(6.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Pergunta: ${item.questionText}",
                                    fontSize = 18.sp,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "Categoria: ${item.category}",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Score: ${item.score ?: "N/A"}",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Percentual: ${item.percentage ?: 0}%",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Significado: ${item.meaning ?: "Significado fixo"}",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Observação: ${item.note ?: "Observação fixa"}",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }


}
