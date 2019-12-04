package id.infiniteuny.academichandbook.ui.main.home

import android.annotation.SuppressLint
import id.infiniteuny.academichandbook.data.remote.ApiClient
import id.infiniteuny.academichandbook.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 25/11/19
 */
class HomePresenter(private val view: HomeView) {

    @SuppressLint("CheckResult")
    fun getFaculty(){
        view.isLoading(true)
        ApiClient.getService().getFaculty()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view.isLoading(false)
                view.showData(it.result)
            },this::onError)
    }

    private fun onError(t:Throwable){
        logE(t.localizedMessage)
    }
}