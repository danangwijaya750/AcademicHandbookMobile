package id.infiniteuny.academichandbook.ui.lecture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.ExpertiseModel
import id.infiniteuny.academichandbook.data.model.LectureModel
import id.infiniteuny.academichandbook.data.model.LectureModel.Result
import id.infiniteuny.academichandbook.data.model.ResearchModel
import id.infiniteuny.academichandbook.data.model.ScheduleModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_lecture.iv_head
import kotlinx.android.synthetic.main.activity_detail_lecture.rv_expertises
import kotlinx.android.synthetic.main.activity_detail_lecture.rv_research
import kotlinx.android.synthetic.main.activity_detail_lecture.swiper
import kotlinx.android.synthetic.main.activity_detail_lecture.tv_email
import kotlinx.android.synthetic.main.activity_detail_lecture.tv_gender
import kotlinx.android.synthetic.main.activity_detail_lecture.tv_subtitle
import kotlinx.android.synthetic.main.activity_detail_lecture.tv_title

class DetailLectureActivity : AppCompatActivity() {
    private lateinit var dataLecture:LectureModel.Result

    private val dataExpertise= mutableListOf<ExpertiseModel.Result>()
    private val dataResearc= mutableListOf<ResearchModel.Result>()

    private val rvExpertise=object : RvAdapter<Any>(dataExpertise){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder =ExpertiseVH(view)
    }

    private val rvResearch=object : RvAdapter<Any>(dataResearc){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder =ResearchVH(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_detail_lecture)
        supportActionBar?.hide()
        dataLecture = intent.getParcelableExtra("data") as Result

        tv_title.text=dataLecture.name
        tv_subtitle.text=dataLecture.nip
        tv_gender.text=dataLecture.gender
        tv_email.text=dataLecture.email
        Glide.with(this).load(dataLecture.photo).into(iv_head)

        rv_expertises.apply {
            adapter=rvExpertise
            layoutManager=LinearLayoutManager(this@DetailLectureActivity)
        }
        rv_research.apply {
            adapter=rvResearch
            layoutManager=LinearLayoutManager(this@DetailLectureActivity)
        }


        getLectureExpertise()
        getLectureResearch()
        swiper.setOnRefreshListener {
            getLectureExpertise()
            getLectureResearch()
        }
    }

    @SuppressLint("CheckResult")
    private fun getLectureExpertise(){
        swiper.isRefreshing=true
        ApiClient.getService().getLectureExpertise(dataLecture.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showExp(it.result!!)
            },this::onError)
    }

    private fun onError(t:Throwable){
        swiper.isRefreshing=false
        logE(t.message)
    }

    fun showResearch(data:List<ResearchModel.Result>){
        swiper.isRefreshing=false
        dataResearc.clear()
        dataResearc.addAll(data)
        rvResearch.notifyDataSetChanged()
    }
    fun showExp(data:List<ExpertiseModel.Result>){
        swiper.isRefreshing=false
        dataExpertise.clear()
        dataExpertise.addAll(data)
        rvExpertise.notifyDataSetChanged()
    }

    @SuppressLint("CheckResult")
    private fun getLectureResearch(){
        swiper.isRefreshing=true
        ApiClient.getService().getLectureResearch(dataLecture.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showResearch(it.result!!)
            },this::onError)
    }
}
