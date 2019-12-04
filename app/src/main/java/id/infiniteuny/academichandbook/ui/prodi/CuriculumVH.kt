package id.infiniteuny.academichandbook.ui.prodi

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.CuriculumModel
import id.infiniteuny.academichandbook.data.model.ProdiModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.tv_subtitle
import kotlinx.android.synthetic.main.item_faculty.tv_title
import kotlinx.android.synthetic.main.item_list.tv_subtitle2

/**
 * Created by wijaya on 27/11/19
 */
class CuriculumVH(override val containerView: View) : RecyclerView.ViewHolder(containerView)
    , RvAdapter.Binder<CuriculumModel.Result>, LayoutContainer {

    override fun bindData(data: CuriculumModel.Result) {
        tv_title.text = " Mata Kuliah : ${data.matakuliah}"
        tv_subtitle.text = " SKS : ${data.sks}"
        tv_subtitle2.visibility=View.VISIBLE
        tv_subtitle2.text = " Semester : ${data.semester}"


//        itemView.setOnClickListener {
//            val intent = Intent(containerView.context!!, ProdiDetailActivity::class.java)
//            intent.putExtra("data", data)
//            containerView.context.startActivity(intent)
//        }
    }
}