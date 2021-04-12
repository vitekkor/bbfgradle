// Original bug: KT-10267

open class JClass() {
    fun test(s: String){}
}

class Example : JClass() {
    
    private var obj: JClass? = this

    init {
        {
            obj?.test("1")
        }()

        object {
            fun run () {
                obj?.test("2")
            }
        }.run()

        class A {
            fun run () {
                obj?.test("3")
            }
        }
        A().run()
    }
}

fun box(): String {
    Example()
    return "OK"
}
