package id.infiniteuny.academichandbook.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wijaya on 20/11/19
 */
class ApiClient {
    companion object{
        private fun getConnection():Retrofit{
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://1c5550c7.ngrok.io/achb/")
                .build()
        }

        fun getService():ApiService= getConnection().create(ApiService::class.java)

    }
}