package id.infiniteuny.academichandbook.ui.pedoman

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.JenisPedomanModel
import id.infiniteuny.academichandbook.data.model.PedomanModel
import id.infiniteuny.academichandbook.ui.major.MajorActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.iv_image
import kotlinx.android.synthetic.main.item_faculty.tv_subtitle
import kotlinx.android.synthetic.main.item_faculty.tv_title
import kotlinx.android.synthetic.main.item_list.tv_subtitle2

/**
 * Created by wijaya on 27/11/19
 */
class PedomanVH(override val containerView: View): RecyclerView.ViewHolder(containerView)
    , RvAdapter.Binder<PedomanModel.Result>, LayoutContainer {
    override fun bindData(data: PedomanModel.Result) {
        tv_title.text=data.title
        tv_subtitle.visibility=View.VISIBLE
        tv_subtitle.text=data.jenis

        itemView.setOnClickListener {
            val intent= Intent(containerView.context!!, DetailPedomanActivity::class.java)
            intent.putExtra("data",data)
            intent.putExtra("caller","jenis")
            containerView.context.startActivity(intent)
        }

    }

}