package id.infiniteuny.academichandbook.ui.pedoman

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import id.infiniteuny.academichandbook.R
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.PedomanModel
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logD
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pedoman.rv_pedoman

class PedomanActivity : AppCompatActivity() {

    private val dataPedoman= mutableListOf<PedomanModel.Result>()
    private val rvAdapter=object : RvAdapter<Any>(dataPedoman){
        override fun layoutId(position: Int, obj: Any): Int =R.layout.item_list

        override fun viewHolder(view: View, viewType: Int): ViewHolder =PedomanVH(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedoman)

        rv_pedoman.apply {
            adapter=rvAdapter
            layoutManager=LinearLayoutManager(this@PedomanActivity)
        }

        if(intent.getStringExtra("caller")=="search"){
               searchPedoman(intent.getStringExtra("data"))
        }else{
                getPedomanById(intent.getStringExtra("data"))
        }


    }
    @SuppressLint("CheckResult")
    private fun searchPedoman(param:String){
        logD(param)
        ApiClient.getService().searchPedoman(param)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showResult(it.result!!)
            },this::onError)
    }

    @SuppressLint("CheckResult")
    private fun getPedomanById(id:String){
        ApiClient.getService().getPedoman(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showResult(it.result!!)
            },this::onError)
    }

    private fun showResult(data:List<PedomanModel.Result>){
        dataPedoman.clear()
        dataPedoman.addAll(data)
        rvAdapter.notifyDataSetChanged()
    }

    private fun onError(t:Throwable){
        logE(t.localizedMessage)
    }
}
