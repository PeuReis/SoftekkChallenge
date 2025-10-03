import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("questions")
    fun getQuestions(): Call<List<Question>>

    @POST("questions/answer")
    fun answer(@Body answer: Answer): Call<Answer>

    @GET("analysis")
    fun getRelatorio(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Call<Analysis>
}