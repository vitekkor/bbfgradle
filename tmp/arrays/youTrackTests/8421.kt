// Original bug: KT-13454

open class ClickEvent(val callback: () -> Unit) {
    fun doThings() {
        callback()
    }
}

class Foo {
    val myString: String = ""

    private inner class BarClickEvent : ClickEvent({
        myString.length // This errors out
    })
}
