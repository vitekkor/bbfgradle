// Original bug: KT-34278

import java.io.Serializable

interface Icon
class ImageIcon : Icon, Serializable
enum class Icons : Icon {
    MyIcon
}

val x: ImageIcon? = null
val y = x ?: Icons.MyIcon // Any
