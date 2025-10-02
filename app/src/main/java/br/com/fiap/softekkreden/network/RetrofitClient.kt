object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/api/" // se rodar local, no emulador Android

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
