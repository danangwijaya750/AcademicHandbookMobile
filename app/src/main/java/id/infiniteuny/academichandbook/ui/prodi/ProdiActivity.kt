package id.infiniteuny.academichandbook.ui.prodi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.FacultyModel.Result
import id.infiniteuny.academichandbook.data.model.MajorModel
import id.infiniteuny.academichandbook.data.model.ProdiModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.ui.major.MajorVH
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_major.rv_major
import kotlinx.android.synthetic.main.activity_major.tv_subtitle
import kotlinx.android.synthetic.main.activity_major.tv_title
import kotlinx.android.synthetic.main.activity_prodi.rv_prodi
import kotlinx.android.synthetic.main.activity_prodi.swipe_prodi
import kotlinx.android.synthetic.main.activity_prodi.tv_visimisi
import kotlinx.android.synthetic.main.fragment_home.iv_head

class ProdiActivity : AppCompatActivity() {

    lateinit var dataMajor: MajorModel.Result
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prodi)
        dataMajor= intent.getParcelableExtra("data") as MajorModel.Result
        tv_title.text = dataMajor.name
        tv_subtitle.text = dataMajor.website
//        val strings=dataMajor.visiMisi!!.split("`").toTypedArray()
        tv_visimisi.text =dataMajor.visiMisi
        rv_prodi.apply {
            adapter=rvAdapter
            layoutManager= LinearLayoutManager(this@ProdiActivity)
        }
        getProdi()
        swipe_prodi.setOnRefreshListener {
            getProdi()
        }
    }

    private val dataProdi = mutableListOf<ProdiModel.Result>()
    private val rvAdapter = object : RvAdapter<Any>(dataProdi) {
        override fun layoutId(position: Int, obj: Any): Int = R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder = ProdiVH(view)
    }

    @SuppressLint("CheckResult")
    private fun getProdi() {
        swipe_prodi.isRefreshing=true
        ApiClient.getService().getProdi(dataMajor.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showData(it.result!!)
            },this::onError)

    }


    private fun showData(data:List<ProdiModel.Result>){
        swipe_prodi.isRefreshing=false
        dataProdi.clear()
        dataProdi.addAll(data)
        rvAdapter.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        swipe_prodi.isRefreshing=false
        logE(t.localizedMessage)
    }
}
