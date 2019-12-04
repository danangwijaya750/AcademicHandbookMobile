package id.infiniteuny.academichandbook.ui.main.schedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.ScheduleModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_schedule.rv_schedule
import kotlinx.android.synthetic.main.fragment_schedule.swipe_schedule

/**
 * Created by wijaya on 21/11/19
 */
class ScheduleFragment : Fragment() {

    companion object {
        fun getInstance(): ScheduleFragment = ScheduleFragment()
    }

    private val dataSchedule= mutableListOf<ScheduleModel.Result>()
    private val rvAdapter=object:RvAdapter<Any>(dataSchedule){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder =ScheduleVH(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_schedule.apply {
            adapter=rvAdapter
            layoutManager=LinearLayoutManager(this@ScheduleFragment.context!!)
        }

        getSchdule()
        swipe_schedule.setOnRefreshListener {
            getSchdule()
        }
    }

    @SuppressLint("CheckResult")
    private fun getSchdule(){
        swipe_schedule.isRefreshing=true
        ApiClient.getService().getStudentSchedule()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showData(it.result!!)
            },this::onError)
    }

    private fun showData(data:List<ScheduleModel.Result>){
        swipe_schedule.isRefreshing=false
        dataSchedule.clear()
        dataSchedule.addAll(data)
        rvAdapter.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        swipe_schedule.isRefreshing=false
        logE(t.localizedMessage)
    }
}