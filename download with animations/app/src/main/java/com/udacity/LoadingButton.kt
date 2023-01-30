package com.udacity

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.abs
import kotlin.math.nextTowards
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0

    // for Text
    private var text = ""
    private var textX = 0f
    private var textY = 0f

    // for circle
    private var path = Path()
    private var left = 0f
    private var right = 0f
    private var top = 0f
    private var bottom = 0f

    // Default Attributes
    private var label = context.getString(R.string.button_name)
    private var labelColor = Color.WHITE
    private var loadingLabel = context.getString(R.string.button_loading)
    private var backColor = context.getColor(R.color.colorPrimary)
    private var loadingColor = context.getColor(R.color.colorPrimaryDark)
    private var contentDesc = context.getString(R.string.app_description)

    @Volatile
    private var progress = 0f

    private var valueAnimator = ValueAnimator()

    private val updateListener = ValueAnimator.AnimatorUpdateListener {
        progress = it.animatedValue as Float
        invalidate()
        if (progress == 100f)
            hasCompletedDownload()
    }

    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

    }

    init {
        isClickable = true
        // set attributes values
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            label = getString(R.styleable.LoadingButton_text) ?: label
            labelColor = getColor(R.styleable.LoadingButton_textColor, labelColor)
            loadingLabel = getString(R.styleable.LoadingButton_loadingText) ?: loadingLabel
            backColor = getColor(R.styleable.LoadingButton_backColor, backColor)
            loadingColor = getColor(R.styleable.LoadingButton_loadingColor, loadingColor)
            contentDescription = getString(R.styleable.LoadingButton_contentDesc) ?: contentDesc
        }
        // inflate the animation xml
        valueAnimator = AnimatorInflater.loadAnimator(
            context,
            R.animator.loading_animation
        ) as ValueAnimator
        // set the AnimatorUpdateListener
        valueAnimator.addUpdateListener(updateListener)

    }

    override fun performClick(): Boolean {
        // to give access to onClickEventListener
        super.performClick()

        if (buttonState == ButtonState.Completed) {
            buttonState = ButtonState.Clicked
            // downloading code...
            buttonState = ButtonState.Loading
            valueAnimator.start()
        }
        invalidate()
        return true
    }

    /**
     * Add Public Function to be called when the download is completed
     */
    fun hasCompletedDownload() {
        valueAnimator.cancel()
        buttonState = ButtonState.Completed
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        widthSize = w
        heightSize = h
        textX = widthSize / 2f
        textY = (heightSize / 2f) - ((paint.descent() + paint.ascent()) / 2)
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        // from 20sp to float
        textSize = 20 * resources.displayMetrics.scaledDensity
        typeface = Typeface.create("Arial", Typeface.NORMAL)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = backColor
        canvas?.drawRect(widthSize.toFloat(), heightSize.toFloat(), 0f, 0f, paint)
        text = label

        // case of Loading
        if (buttonState == ButtonState.Loading) {
            paint.color = loadingColor
            canvas?.drawRect(widthSize * (progress / 100f), heightSize.toFloat(), 0f, 0f, paint)
            text = loadingLabel

            // draw the loading arc for animated circle
            left = textX + paint.measureText(text) / 2
            right = left - paint.ascent() + paint.descent()
            top = textY + paint.ascent()
            bottom = textY + paint.descent()

            paint.color = Color.YELLOW
            canvas?.drawArc(left, top, right, bottom,
                0f, progress * 3.6f,true,paint)

//            path.reset()
//            path.addArc(
//                left, top, right, bottom,
//                0f, progress * 3.6f
//            )
//            canvas?.drawPath(path, paint)
        }

        // draw the button text
        paint.color = labelColor
        canvas?.drawText(text, textX, textY, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

}