package id.infiniteuny.academichandbook.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 25/11/19
 */
data class ProdiModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {
    @Parcelize
    data class Result(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("major_id")
        var majorId: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("skkni_id")
        var skkniId: String? = null,
        @SerializedName("visi_misi")
        var visiMisi: String? = null,
        @SerializedName("website")
        var website: String? = null
    ):Parcelable
}