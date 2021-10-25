// Original bug: KT-5668

package bug2

fun bar(style: String="", init: () -> Unit) {}
fun foo(init: () -> Unit) {}

class Page() {

    fun doit() {
        var yes = false
        foo {
            val styleShow = "visibility:visible;"
            val styleHide = "visibility:hidden;"
            bar(style="${if (yes) styleShow else styleHide }") {  }
        }
    }
}
