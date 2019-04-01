package karrel.kr.co.imagememorytest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * Created by Rell on 2019. 4. 1..
 */
class CustomImageView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {

    private val images = arrayOf(
            R.drawable.num0,
            R.drawable.num1,
            R.drawable.num2,
            R.drawable.num3,
            R.drawable.num4,
            R.drawable.num5,
            R.drawable.num6,
            R.drawable.num7,
            R.drawable.num8,
            R.drawable.num9
    )

    private var bitmap: Bitmap? = null

    init {

        for (i in 0 until 10) {
            images.forEach { imageRes ->
                val b = BitmapFactory.decodeResource(context.resources, imageRes)
                if (bitmap == null) {
                    bitmap = b
                } else {
                    bitmap = mergeToPin(bitmap!!, b)
                }
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()

        canvas?.drawBitmap(bitmap, 0f, 0f, paint)


    }

    private fun mergeToPin(back: Bitmap, front: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(back.width, back.height, back.config)
        val canvas = Canvas(result)
        val widthBack = back.width
        val widthFront = front.width
        val move = ((widthBack - widthFront) / 2).toFloat()
        canvas.drawBitmap(back, 0f, 0f, null)
        canvas.drawBitmap(front, move, move, null)
        return result
    }

}