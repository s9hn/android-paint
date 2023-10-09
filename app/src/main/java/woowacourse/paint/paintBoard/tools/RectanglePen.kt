package woowacourse.paint.paintBoard.tools

import android.graphics.Path
import woowacourse.paint.paintBoard.Line

class RectanglePen(
    override val onSave: (line: Line) -> Unit,
    override val line: Line
) : ShapePen(onSave) {

    override fun draw(pointX: Float, pointY: Float) {
        val left = minOf(startingPointX, pointX)
        val top = minOf(startingPointY, pointY)
        val right = maxOf(startingPointX, pointX)
        val bottom = maxOf(startingPointY, pointY)

        line.path.addRect(left, top, right, bottom, Path.Direction.CCW)
    }
}