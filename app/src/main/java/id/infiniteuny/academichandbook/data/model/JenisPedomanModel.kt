package id.infiniteuny.academichandbook.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 27/11/19
 */
data class JenisPedomanModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {
    @Parcelize
    data class Result(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("name")
        var name: String? = null
    ):Parcelable
}