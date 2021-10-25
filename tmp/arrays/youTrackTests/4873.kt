// Original bug: KT-21402

val someComponent: JComponent? = null

class JComponent
operator fun <T : JComponent> T.invoke() {}
fun main(args: Array<String>) {
    someComponent!!()
}
