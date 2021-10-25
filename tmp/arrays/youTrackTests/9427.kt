// Original bug: KT-13990

interface Context
interface AttributeSet
class Matrix
interface PositionAnimation
class Point
interface Bitmap
class RectF

class ExampleButton {
    constructor(context: Context?)
    constructor(context: Context?, attrs: AttributeSet?)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)

    var isDoubleAvatar: Boolean = false
    private var avatarPosition: Float = newAvatarPosition(isDoubleAvatar)
    private fun newAvatarPosition(isDoubleAvatar: Boolean) = if (isDoubleAvatar) 0f else 1f

    private lateinit var staticAvatar: Bitmap
    private lateinit var movingAvatar: Bitmap

    private val staticAvatarPosition = Point()
    private val movingAvatarPosition = Point()

    private val srcRect = RectF()

    private val m = Matrix()
    private var positionAnimator: PositionAnimation? = null
}
