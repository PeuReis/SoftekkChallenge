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
                    _questions.value = response.body()
                } else {
                    println("Erro: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                println("Falha: ${t.message}")
            }
        })
    }
}
