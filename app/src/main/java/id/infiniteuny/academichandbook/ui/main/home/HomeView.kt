package id.infiniteuny.academichandbook.ui.main.home

import id.infiniteuny.academichandbook.data.model.FacultyModel

/**
 * Created by wijaya on 25/11/19
 */
interface HomeView {
    fun isLoading(state:Boolean)
    fun isError(msg:String)
    fun showData(data:List<FacultyModel.Result>?)
}