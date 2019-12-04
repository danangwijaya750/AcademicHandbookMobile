package id.infiniteuny.academichandbook.ui.main.pedoman

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.JenisPedomanModel
import id.infiniteuny.academichandbook.data.model.PedomanModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.ui.pedoman.PedomanActivity
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pedoman.rv_jenis_pedoman
import kotlinx.android.synthetic.main.fragment_pedoman.search_view
import kotlinx.android.synthetic.main.fragment_pedoman.swipe_pedoman

/**
 * Created by wijaya on 21/11/19
 */
class PedomanFragment : Fragment() {

    companion object {
        fun getInstance(): PedomanFragment = PedomanFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pedoman,container,false)
    }

    @SuppressLint("CheckResult")
    private fun getJenisPedoman(){
        swipe_pedoman.isRefreshing=true
        ApiClient.getService().getJenisPedoman()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showData(it.result!!)
            },this::onError)
    }

    fun showData(data:List<JenisPedomanModel.Result>){
        swipe_pedoman.isRefreshing=false
        dataJenis.clear()
        dataJenis.addAll(data)
        rvAdapter.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        logE(t.localizedMessage)
    }
    val dataJenis= mutableListOf<JenisPedomanModel.Result>()
    val rvAdapter=object:RvAdapter<Any>(dataJenis){
        override fun layoutId(position: Int, obj: Any): Int = R.layout.item_list
        override fun viewHolder(view: View, viewType: Int): ViewHolder = JenisPedomanVH(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_jenis_pedoman.apply {
            adapter=rvAdapter
            layoutManager=LinearLayoutManager(this@PedomanFragment.context!!)
        }

        getJenisPedoman()

        search_view.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent=Intent(this@PedomanFragment.context!!,PedomanActivity::class.java)
                intent.putExtra("caller","search")
                intent.putExtra("data",query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        })
    }
}