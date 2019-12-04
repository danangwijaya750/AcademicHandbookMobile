package id.infiniteuny.academichandbook.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 25/11/19
 */

data class LectureModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {

    @Parcelize
    data class Result(
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("gender")
        var gender: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("nidn")
        var nidn: String? = null,
        @SerializedName("nip")
        var nip: String? = null,
        @SerializedName("phone")
        var phone: String? = null,
        @SerializedName("photo")
        var photo: String? = null,
        @SerializedName("prodi_name")
        var prodiName: String? = null
    ) : Parcelable
}