package id.infiniteuny.academichandbook.ui.major

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.FacultyModel.Result
import id.infiniteuny.academichandbook.data.model.MajorModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_major.rv_major
import kotlinx.android.synthetic.main.activity_major.swipe_major
import kotlinx.android.synthetic.main.activity_major.tv_subtitle
import kotlinx.android.synthetic.main.activity_major.tv_title
import kotlinx.android.synthetic.main.fragment_home.iv_head

class MajorActivity : AppCompatActivity() {

    lateinit var dataFaculty: FacultyModel.Result
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)
        dataFaculty = intent.getParcelableExtra("data") as Result
        Glide.with(this).load(dataFaculty.photo).into(iv_head)
        tv_title.text = dataFaculty.name
        tv_subtitle.text = dataFaculty.website

        rv_major.apply {
            adapter=rvAdapter
            layoutManager=LinearLayoutManager(this@MajorActivity)
        }
        getMajor()

        swipe_major.setOnRefreshListener {
            getMajor()
        }
    }

    private val dataMajor = mutableListOf<MajorModel.Result>()
    private val rvAdapter = object : RvAdapter<Any>(dataMajor) {
        override fun layoutId(position: Int, obj: Any): Int = R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder = MajorVH(view)
    }

    @SuppressLint("CheckResult")
    private fun getMajor() {
        swipe_major.isRefreshing=true
        ApiClient.getService().getMajor(dataFaculty.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showData(it.result!!)
            },this::onError)

    }

    private fun showData(data:List<MajorModel.Result>){
        swipe_major.isRefreshing=false
        dataMajor.clear()
        dataMajor.addAll(data)
        rvAdapter.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        swipe_major.isRefreshing=false
        logE(t.localizedMessage)
    }
}
