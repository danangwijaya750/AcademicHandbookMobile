package id.infiniteuny.academichandbook.data.remote

import id.infiniteuny.academichandbook.data.model.CuriculumModel
import id.infiniteuny.academichandbook.data.model.ExpertiseModel
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.JenisPedomanModel
import id.infiniteuny.academichandbook.data.model.LectureModel
import id.infiniteuny.academichandbook.data.model.MajorModel
import id.infiniteuny.academichandbook.data.model.PedomanModel
import id.infiniteuny.academichandbook.data.model.ProdiModel
import id.infiniteuny.academichandbook.data.model.ResearchModel
import id.infiniteuny.academichandbook.data.model.ScheduleModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wijaya on 20/11/19
 */
interface ApiService {
    @GET("faculty.php")
    fun getFaculty(): Observable<FacultyModel>

    @GET("majorInFaculty.php")
    fun getMajor(@Query("id") id:String):Observable<MajorModel>

    @GET("prodiInMajor.php")
    fun getProdi(@Query("id") id: String):Observable<ProdiModel>

    @GET("curiculum.php")
    fun getCuriculum(@Query("id") id:String):Observable<CuriculumModel>

    @GET("jenisPedoman.php")
    fun getJenisPedoman(): Observable<JenisPedomanModel>

    @GET("pedomanByType.php")
    fun getPedoman(@Query("id") id: String):Observable<PedomanModel>

    @GET("pedomanSearch.php")
    fun searchPedoman(@Query("param") param: String):Observable<PedomanModel>

    @GET("lectureInProdi.php")
    fun getLectureInProdi(@Query("id") id: String):Observable<LectureModel>

    @GET("studentSchedule.php")
    fun getStudentSchedule(): Observable<ScheduleModel>

    @GET("lectureExpertise.php")
    fun getLectureExpertise(@Query("id") id:String): Observable<ExpertiseModel>

    @GET("lectureResearch.php")
    fun getLectureResearch(@Query("id") id:String): Observable<ResearchModel>
}