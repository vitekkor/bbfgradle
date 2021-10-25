// Original bug: KT-9126

data class My(val x: Int?)

fun foo() {
    var y: My? = My(42)
    // y!! is important here and below, with just y.x we have no bug
    if (y!!.x != null) {
        y = My(null)
        // We perform a smart cast here for y!!.x but we should not
        y!!.x.hashCode()
    }
}
