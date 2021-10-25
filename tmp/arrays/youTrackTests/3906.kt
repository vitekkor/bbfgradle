// Original bug: KT-14455

fun main(args: Array<String>) {    
    val holder = SimpleHolder(View())
}

abstract class Holder(rootView: View) {
    abstract val view : View
    
    init {
        view.layout()
    }
}

class SimpleHolder(rootView:View) : Holder(rootView) {
    override val view: View = rootView.findViewById(0)
}

class View() {
    fun findViewById(id: Int) : View = View()
    
    fun layout() {
        println("Layout!")
    }
}
