interface ApiService {

    @GET("questions")
    fun getQuestions(): Call<List<QuestionsResponse>>

    @POST("questions/answer")
    fun answer(@Body answer: Answer): Call<Answer>

    @GET("analysis")
    fun getRelatorio(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Call<AnalysisResponse>
}