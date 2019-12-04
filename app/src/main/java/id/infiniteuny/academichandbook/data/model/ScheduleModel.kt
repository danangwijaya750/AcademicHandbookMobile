package id.infiniteuny.academichandbook.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 27/11/19
 */
data class ScheduleModel(
    @SerializedName("result")
    var result: List<Result>? = null
) {
    @Parcelize
    data class Result(
        @SerializedName("course_name")
        var courseName: String? = null,
        @SerializedName("day_schedule")
        var daySchedule: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("lecture_name")
        var lectureName: String? = null,
        @SerializedName("time_schedule")
        var timeSchedule: String? = null
    ):Parcelable
}