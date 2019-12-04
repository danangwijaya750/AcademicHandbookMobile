package id.infiniteuny.academichandbook.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by wijaya on 27/11/19
 */
data class ExpertiseModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {

    data class Result(
        @SerializedName("expertises")
        var expertises: String? = null
    )
}