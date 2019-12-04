package id.infiniteuny.academichandbook.ui.prodi

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Contacts.Intents
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.infiniteuny.academichandbook.base.RvAdapter
import id.infiniteuny.academichandbook.data.model.FacultyModel
import id.infiniteuny.academichandbook.data.model.LectureModel
import id.infiniteuny.academichandbook.ui.lecture.DetailLectureActivity
import id.infiniteuny.academichandbook.ui.major.MajorActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.iv_image
import kotlinx.android.synthetic.main.item_faculty.tv_subtitle
import kotlinx.android.synthetic.main.item_faculty.tv_title

/**
 * Created by wijaya on 25/11/19
 */
class LectureVH  (override val containerView: View): RecyclerView.ViewHolder(containerView)
    , RvAdapter.Binder<LectureModel.Result>, LayoutContainer {
    override fun bindData(data: LectureModel.Result) {
        tv_title.text=data.name
        tv_subtitle.text=data.nip.toString()
        Glide.with(containerView).load(data.photo.toString()).into(iv_image)

        itemView.setOnClickListener {
            val intent=Intent(containerView.context!!,DetailLectureActivity::class.java)
            intent.putExtra("data",data)
            containerView.context.startActivity(intent)
        }

    }

}