package id.infiniteuny.academichandbook.ui.major

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.MajorModel
import id.infiniteuny.academichandbook.ui.prodi.ProdiActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.tv_subtitle
import kotlinx.android.synthetic.main.item_faculty.tv_title

/**
 * Created by wijaya on 26/11/19
 */
class MajorVH(override val containerView: View) : RecyclerView.ViewHolder(containerView)
    , RvAdapter.Binder<MajorModel.Result>, LayoutContainer {

    override fun bindData(data: MajorModel.Result) {
        tv_title.text = data.name
        tv_subtitle.text = data.website.toString()

        itemView.setOnClickListener {
            val intent = Intent(containerView.context!!, ProdiActivity::class.java)
            intent.putExtra("data", data)
            containerView.context.startActivity(intent)
        }
    }
}