package revolhope.splanes.com.aikver.presentation.common

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import revolhope.splanes.com.core.domain.model.UserAvatar

fun dpToPx(context: Context, dp: Int): Int =
    dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun View.visibility(show: Boolean) = if (show) this.visible() else this.invisible()

fun ImageView.loadCircular(url: String) {
    if (url.isNotBlank()) Glide.with(context).load(url).circleCrop().into(this)
}

fun ImageView.loadAvatar(avatar: UserAvatar) {
    val baseUrl = "https://api.adorable.io/avatars/face"
    Glide.with(context)
        .load("$baseUrl/${avatar.eyes}/${avatar.nose}/${avatar.mouth}/${avatar.color}")
        .circleCrop()
        .into(this)
}