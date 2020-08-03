package revolhope.splanes.com.aikver.presentation.feature.menu.common.customcontent.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.component_custom_content_punctuation_view.view.emptyStatePunctuation
import kotlinx.android.synthetic.main.component_custom_content_punctuation_view.view.punctuationAverage
import kotlinx.android.synthetic.main.component_custom_content_punctuation_view.view.punctuationButton
import kotlinx.android.synthetic.main.component_custom_content_punctuation_view.view.punctuationRecycler
import revolhope.splanes.com.aikver.R
import revolhope.splanes.com.aikver.presentation.common.invisible
import revolhope.splanes.com.aikver.presentation.common.toLowerCase
import revolhope.splanes.com.aikver.presentation.common.visibility
import revolhope.splanes.com.aikver.presentation.common.visible
import revolhope.splanes.com.core.domain.model.content.ContentDetails
import revolhope.splanes.com.core.domain.model.content.CustomContent
import revolhope.splanes.com.core.domain.model.content.serie.SerieDetails
import revolhope.splanes.com.core.domain.model.user.User

class CustomContentPunctuationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    attrDefStyle: Int = 0
) : LinearLayout(context, attributeSet, attrDefStyle) {

    init {
        View.inflate(context, R.layout.component_custom_content_punctuation_view, this)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    fun setupPunctuation(currentUser: User, customContent: CustomContent<ContentDetails>) {
        if (customContent.punctuation.isEmpty()) {
            setupEmptyState(customContent.content is SerieDetails)
            punctuationButton.invisible()
            punctuationAverage.invisible()
            punctuationRecycler.invisible()
            emptyStatePunctuation.visible()
        } else {
            punctuationAverage.text =
                (customContent.punctuation.sumBy { (it.second * 10).toInt() } / (10 * customContent.punctuation.size)).toString()
            punctuationRecycler.layoutManager = LinearLayoutManager(context)
            punctuationRecycler.adapter = CustomContentUserAdapter(
                items = customContent.punctuation.map {
                    CustomContentUserUiModel(
                        userId = it.first.userId,
                        avatar = it.first.avatar,
                        userName = it.first.username,
                        punctuation = it.second
                    )
                },
                currentUserId = currentUser.id
            )
            punctuationButton.visibility(
                customContent.punctuation.any { it.first.userId == currentUser.id }.not()
            )
        }
    }

    private fun setupEmptyState(isSerie: Boolean) {
        val contentText = context.getString(if (isSerie) R.string.serie else R.string.film)
        emptyStatePunctuation.setTitle(
            context.getString(
                R.string.content_no_scored,
                contentText.toLowerCase()
            )
        )
        emptyStatePunctuation.setActionText(context.getString(R.string.score_now))
        emptyStatePunctuation.setAction {
            Toast.makeText(context, "TODO!", Toast.LENGTH_LONG).show()
        }

    }

}