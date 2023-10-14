package woowacourse.paint.paintBoard.painter.tool.erase

import android.graphics.RectF
import woowacourse.paint.paintBoard.Line

class PathEraser(
    private val onRemove: (Line) -> Unit,
    private val painting: MutableList<Line>,
) : EraseTool() {

    override fun startPainting(pointX: Float, pointY: Float) {
        erase(pointX, pointY)
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        erase(pointX, pointY)
    }

    private fun erase(pointX: Float, pointY: Float) {
        painting.forEach { line ->
            val bounds = RectF()
            line.path.computeBounds(bounds, true)
            if (bounds.contains(pointX, pointY)) {
                onRemove(line)
                return
            }
        }
    }
}
