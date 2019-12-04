package id.infiniteuny.academichandbook.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.FacultyModel.Result
import id.infiniteuny.academichandbook.util.logD
import id.infiniteuny.academichandbook.util.toast
import kotlinx.android.synthetic.main.fragment_home.rv_faculty
import kotlinx.android.synthetic.main.fragment_home.swipe_faculty

/**
 * Created by wijaya on 21/11/19
 */
class HomeFragment : Fragment(), HomeView {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    private val dataFaculty = mutableListOf<FacultyModel.Result>()
    private lateinit var presenter: HomePresenter
    private val rvAdapter = object : RvAdapter<Any>(dataFaculty) {
        override fun layoutId(position: Int, obj: Any): Int = R.layout.item_faculty

        override fun viewHolder(view: View, viewType: Int): ViewHolder = FacultyVH(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)

        rv_faculty.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }

        presenter.getFaculty()

        swipe_faculty.setOnRefreshListener {
            presenter.getFaculty()
        }
    }

    override fun isLoading(state: Boolean) {
        swipe_faculty.isRefreshing=state
    }

    override fun isError(msg: String) {
    }

    override fun showData(data: List<Result>?) {
        if (data!!.isNotEmpty()) {
            logD(data.size.toString())
            dataFaculty.clear()
            dataFaculty.addAll(data)
            rvAdapter.notifyDataSetChanged()
        }
    }
}