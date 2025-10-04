package br.com.fiap.softekkreden.model

import Analysis
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalysisViewModel : ViewModel() {

    private val _analysisList = MutableLiveData<List<Analysis>>()
    val analysisList: LiveData<List<Analysis>> = _analysisList

    fun carregarAnalises(deviceId: String, year: Int, month: Int) {
        RetrofitClient.instance.getAnalysis(deviceId, year, month).enqueue(object : Callback<List<Analysis>> {
            override fun onResponse(
                call: Call<List<Analysis>>,
                response: Response<List<Analysis>>
            ) {
                if (response.isSuccessful) {
                    _analysisList.value = response.body() ?: emptyList()
                    Log.d("AnalysisViewModel", "Análises recebidas: ${_analysisList.value?.size}")
                } else {
                    Log.e("AnalysisViewModel", "Erro no GET: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Analysis>>, t: Throwable) {
                Log.e("AnalysisViewModel", "Falha no GET: ${t.message}")
            }
        })
    }
}
