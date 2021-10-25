// Original bug: KT-30855

@Deprecated("Nice description here", replaceWith = ReplaceWith("isVisible = visible"))
fun C.something(visible: Boolean)  {
    // Something useful here
}

class C(){
    var isVisible: Boolean = false
}

fun use(){
    C().something(false)
}
