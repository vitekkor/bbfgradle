// Original bug: KT-23131

open class Foo {
    var next: String? = null

    init {
        if (this !is Activity) {
            next = null
        } else {
            next = (this as? Activity)?.plugin
        }
    }
}

class Activity: Foo() {
    val plugin = ""
}
