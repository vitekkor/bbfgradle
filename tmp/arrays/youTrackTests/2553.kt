// Original bug: KT-20140

class C(var s: String?) {
    fun m() {
        val s = this.s //inline (Ctrl + Shift + n)  of s should be disallowed because the resulting smart cast below will no longer be possible
        s?.let {
            println(s.length)
        }
    }
}
