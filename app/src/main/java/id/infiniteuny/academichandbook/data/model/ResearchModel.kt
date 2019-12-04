package id.infiniteuny.academichandbook.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by wijaya on 27/11/19
 */
data class ResearchModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {

    data class Result(
        @SerializedName("date_research")
        var dateResearch: String? = null,
        @SerializedName("file")
        var `file`: Any? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("title")
        var title: String? = null
    )
}