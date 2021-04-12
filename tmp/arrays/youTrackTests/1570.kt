// Original bug: KT-28055

fun Color(r: Int, g: Int, b: Int): Color = ColorString("rgb($r,$g,$b)")
fun Color(r: Int, g: Int, b: Int, a: Number): Color = ColorString("rgba($r,$g,$b,$a)")
fun Color(hexString: String): Color = ColorString(if (hexString.first() == '#') hexString else "#$hexString")

interface Color {
    companion object {
        val WHITE: Color = ColorString("#ffffff")
        val BLACK: Color = ColorString("#000000")
        val TRANSPARENT: Color = ColorString("transparent")
        val INHERIT: Color = ColorString("inherit")
        val INITIAL: Color = ColorString("initial")

        internal fun colorString(r: Int, g: Int, b: Int): String = "rgb($r,$g,$b)"
        internal fun colorString(r: Int, g: Int, b: Int, a: Number): String = "rgba($r,$g,$b,$a)"
        internal fun colorString(hexString: String): String =
            if (hexString.first() == '#') hexString else "#$hexString"
    }
}

internal inline class ColorString(val cssValue: String) : Color {
    override fun toString() = cssValue
}
