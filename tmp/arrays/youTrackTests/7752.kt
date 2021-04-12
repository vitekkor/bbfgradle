// Original bug: KT-25246

// ext.kotlin_version = '1.2.60-eap-7'
//
//compileKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//    kotlinOptions.freeCompilerArgs = ["-XXLanguage:+InlineClasses"]
//    kotlinOptions.languageVersion = "1.2"
//}

inline class Rgba(val value: Int) {
    inline val r: Int get() = (value shr 0) and 0xFF
    inline val g: Int get() = (value shr 8) and 0xFF
    inline val b: Int get() = (value shr 16) and 0xFF
    inline val a: Int get() = (value shr 24) and 0xFF
}

fun Rgba(r: Int, g: Int, b: Int, a: Int): Rgba {
    return Rgba(
        ((r and 0xFF) shl 0) or ((g and 0xFF) shl 8) or ((b and 0xFF) shl 16) or ((a and 0xFF) shl 24)
    )
}

fun Rgba.withR(r: Int) = Rgba(r, g, b, a)
fun Rgba.withG(g: Int) = Rgba(r, g, b, a)
fun Rgba.withB(b: Int) = Rgba(r, g, b, a)
fun Rgba.withA(a: Int) = Rgba(r, g, b, a)

inline class RgbaArray(val array: IntArray) {
    constructor(size: Int) : this(IntArray(size))
    operator fun get(index: Int): Rgba = Rgba(array[index])
    operator fun set(index: Int, color: Rgba) {
        array[index] = color.value
    }
}

fun main(args: Array<String>) {
    val result = RgbaArray(32) // Cause exception: AFTER mandatory stack transformations: incorrect bytecode Element is unknownThe root cause was thrown at: MethodVerifier.kt:28
    //val result = RgbaArray(IntArray(32)) // Works
    val color = Rgba(128, 128, 0, 255)
    result[0] = color.withG(64).withA(0)
    println(result[0].value)
}
