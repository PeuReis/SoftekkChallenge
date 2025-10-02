package br.com.fiap.softekkreden.model


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = mutableStateOf<String?>(null) // Or your specific type
    val loginResult: State<String?> = _loginResult

    fun login(username: String, password: String) {
        // Your login logic here
        if (username == "admin" && password == "password") { // Example logic
            _loginResult.value = "OK"
        } else {
            _loginResult.value = "ERRO"
        }
    }

    fun resetResult() {
        _loginResult.value = null
    }
}
