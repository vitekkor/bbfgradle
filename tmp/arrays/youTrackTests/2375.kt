// Original bug: KT-22960

abstract class Widget

abstract class Container : Widget() {
    fun <T : Widget> addWidget(widget: T): T {
        // ...
        return widget
    }
}

open class Box : Container()

open class MyWidget : Box() {
    val box1: Box
    val box2: Box
    
    init {
        box1 = addWidget(Box().apply {
            box2 = addWidget(Box().apply {
                
            })
        })
    }
}
