import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsViewModel : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    fun carregarQuestions() {
        RetrofitClient.instance.getQuestions().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    _questions.value = lista ?: emptyList()
                    Log.d("QuestionsViewModel", "Perguntas recebidas: ${lista?.size}")
                } else {
                    println("Erro: ${response.code()}")
                    Log.e("QuestionsViewModel", "Erro no GET: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Log.e("QuestionsViewModel", "Falha no GET: ${t.message}")
            }
        })
    }
    fun enviarResposta(deviceId:String ,resposta: Answer) {
        RetrofitClient.instance.answer(deviceId ,resposta).enqueue(object : Callback<Answer> {
            override fun onResponse(
                call: Call<Answer?>,
                response: Response<Answer?>
            ) {
                if (response.isSuccessful) {
                    Log.d("QuestionsViewModel", "Resposta enviada com sucesso")
                } else {
                    Log.e("QuestionsViewModel", "Erro ao enviar resposta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Answer?>, t: Throwable) {
                Log.e("QuestionsViewModel", "Falha no POST: ${t.message}")
            }


        })
    }
}
