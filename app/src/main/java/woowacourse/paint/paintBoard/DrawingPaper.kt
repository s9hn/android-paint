package woowacourse.paint.paintBoard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import woowacourse.paint.paintBoard.painter.Painter

class DrawingPaper(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    private lateinit var painter: Painter
    private val painting: MutableList<Line> = mutableListOf()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    fun setPainter(tool: Painter) {
        painter = tool
    }

    fun setPainting(lines: List<Line>) {
        painting.clear()
        painting.addAll(lines)

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        painting.forEach { line -> canvas.drawPath(line.path, line.brush.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> painter.startPainting(event.x, event.y)
            ACTION_MOVE -> painter.drawPainting(event.x, event.y)
            ACTION_UP -> {
                painter.finishPainting()
                return true
            }

            else -> return super.onTouchEvent(event)
        }

        invalidate()
        return true
    }
}

