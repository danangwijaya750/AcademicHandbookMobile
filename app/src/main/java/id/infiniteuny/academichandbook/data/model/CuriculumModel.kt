package id.infiniteuny.academichandbook.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by wijaya on 25/11/19
 */
data class CuriculumModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {

    data class Result(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("matakuliah")
        var matakuliah: String? = null,
        @SerializedName("semester")
        var semester: String? = null,
        @SerializedName("sks")
        var sks: String? = null
    )
}