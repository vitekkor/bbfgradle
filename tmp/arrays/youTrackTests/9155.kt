// Original bug: KT-12806

inline fun font(name: String, size: Int, bold: Boolean = false, italic: Boolean = false) = KFont(name, size, bold, italic)

class KFont(val name: String, val size: Int, val bold: Boolean, val italic: Boolean)
