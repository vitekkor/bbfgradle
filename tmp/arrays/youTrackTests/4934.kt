// Original bug: KT-35120

class Event

fun foo(s: String, event: (Event) -> Unit) {}

class ResizeLogger {
    private fun logResize(event: Event) {  // <-- warning here
        println("Resized")
    }

    fun addListener() {
        foo("resize", ::logResize)
    }

}
