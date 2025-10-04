data class Question(
    val id: String,
    val text: String,
    val category: String,
    val alternatives: List<String>,
    val type: String
)