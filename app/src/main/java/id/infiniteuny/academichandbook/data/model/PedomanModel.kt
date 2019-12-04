package id.infiniteuny.academichandbook.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 25/11/19
 */
data class PedomanModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {
    @Parcelize
    data class Result(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("jenis")
        var jenis: String? = null,
        @SerializedName("konten")
        var konten: String? = null,
        @SerializedName("title")
        var title: String? = null
    ):Parcelable
}