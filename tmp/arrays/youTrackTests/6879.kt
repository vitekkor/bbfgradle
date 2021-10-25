// Original bug: KT-10873

class Html(body: Html.() -> Unit) {
    val stringBuilder = StringBuilder()
    fun append(string: String) = stringBuilder.append(string)
    fun body(action:()-> Unit={}) {
        append("<body>")
        action()
        append("</body>")
    }
 init {
        body()
    }
}
