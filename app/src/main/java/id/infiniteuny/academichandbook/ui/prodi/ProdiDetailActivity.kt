package id.infiniteuny.academichandbook.ui.prodi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.CuriculumModel
import id.infiniteuny.academichandbook.data.model.LectureModel
import id.infiniteuny.academichandbook.data.model.ProdiModel
import id.infiniteuny.academichandbook.data.model.ProdiModel.Result
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_prodi_detail.rv_kurikulum
import kotlinx.android.synthetic.main.activity_prodi_detail.rv_lecture
import kotlinx.android.synthetic.main.activity_prodi_detail.swiper
import kotlinx.android.synthetic.main.activity_prodi_detail.tv_skkni
import kotlinx.android.synthetic.main.activity_prodi_detail.tv_subtitle
import kotlinx.android.synthetic.main.activity_prodi_detail.tv_title
import kotlinx.android.synthetic.main.activity_prodi_detail.tv_visimisi

class ProdiDetailActivity : AppCompatActivity() {


    private val dataKurikulum= mutableListOf<CuriculumModel.Result>()
    private val dataLecture= mutableListOf<LectureModel.Result>()
    private val rvAdapterKuri=object:RvAdapter<Any>(dataKurikulum){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder =CuriculumVH(view)
    }
    private val rvAdapterLect=object:RvAdapter<Any>(dataLecture){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_faculty

        override fun viewHolder(view: View, viewType: Int): ViewHolder =LectureVH(view)
    }

    private lateinit var dataProdi:ProdiModel.Result
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prodi_detail)
        dataProdi=intent.getParcelableExtra("data") as Result

        tv_title.text=dataProdi.name
        tv_subtitle.text=dataProdi.website
        tv_visimisi.text=dataProdi.visiMisi
        tv_skkni.text=dataProdi.skkniId

        rv_kurikulum.apply {
            adapter=rvAdapterKuri
            layoutManager=LinearLayoutManager(this@ProdiDetailActivity)
        }
        rv_lecture.apply {
            adapter=rvAdapterLect
            layoutManager=LinearLayoutManager(this@ProdiDetailActivity)
        }

        swiper.setOnRefreshListener {
            getCurriculum(dataProdi.id!!)
            getLecture(dataProdi.id!!)
        }

        getCurriculum(dataProdi.id!!)
        getLecture(dataProdi.id!!)
    }

    @SuppressLint("CheckResult")
    private fun getCurriculum(id:String){
        swiper.isRefreshing=true
        ApiClient.getService().getCuriculum(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showDataKur(it.result!!)
            },this::onError)
    }


    @SuppressLint("CheckResult")
    private fun getLecture(id: String){
        swiper.isRefreshing=true
        ApiClient.getService().getLectureInProdi(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
            showDataLect(it.result!!)
            },this::onError)
    }

    private fun showDataLect(data:List<LectureModel.Result>){
        swiper.isRefreshing=false
        dataLecture.clear()
        dataLecture.addAll(data)
        rvAdapterLect.notifyDataSetChanged()
    }

    private fun showDataKur(data:List<CuriculumModel.Result>){
        swiper.isRefreshing=false
        dataKurikulum.clear()
        dataKurikulum.addAll(data)
        rvAdapterKuri.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        swiper.isRefreshing=true
        logE(t.localizedMessage)
    }
}
