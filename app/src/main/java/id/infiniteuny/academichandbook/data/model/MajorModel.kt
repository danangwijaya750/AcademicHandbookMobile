package id.infiniteuny.academichandbook.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 25/11/19
 */
data class MajorModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {
    @Parcelize
    data class Result(
        @SerializedName("faculty_id")
        var facultyId: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("visi_misi")
        var visiMisi: String? = null,
        @SerializedName("website")
        var website: String? = null
    ):Parcelable
}