// Original bug: KT-11100

class Div {
    var className: String? = null
    
    companion object {
        operator fun invoke(init: Div.() -> Unit): Div {
            val div = Div()
            div.init()
            return div
        }
    }
}

fun main(args: Array<String>) {
    Div {
        className = "ui container"
    }
}
