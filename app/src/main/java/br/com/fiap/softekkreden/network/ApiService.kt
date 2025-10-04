import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("questions")
    fun getQuestions(): Call<List<Question>>

    @POST("questions/answer")
    fun answer(@Header ("X-Device-ID") deviceId:String,@Body answer: Answer): Call<Answer>

    @GET("analysis")
    fun getAnalysis(
        @Header ("X-Device-ID") deviceId:String,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Call<List<Analysis>>
}