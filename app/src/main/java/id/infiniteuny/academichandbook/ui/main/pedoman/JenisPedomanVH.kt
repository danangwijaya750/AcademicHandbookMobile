package id.infiniteuny.academichandbook.ui.main.pedoman

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.JenisPedomanModel
import id.infiniteuny.academichandbook.ui.major.MajorActivity
import id.infiniteuny.academichandbook.ui.pedoman.PedomanActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.iv_image
import kotlinx.android.synthetic.main.item_faculty.tv_subtitle
import kotlinx.android.synthetic.main.item_faculty.tv_title

/**
 * Created by wijaya on 27/11/19
 */
class JenisPedomanVH(override val containerView: View): RecyclerView.ViewHolder(containerView)
    , RvAdapter.Binder<JenisPedomanModel.Result>, LayoutContainer {
    override fun bindData(data: JenisPedomanModel.Result) {
        tv_title.text=data.name
        tv_subtitle.visibility=View.GONE


        itemView.setOnClickListener {
            val intent= Intent(containerView.context!!, PedomanActivity::class.java)
            intent.putExtra("data",data.id)
            intent.putExtra("caller","jenis")
            containerView.context.startActivity(intent)
        }

    }

}