// Original bug: KT-10267

open class JClass() {
    fun test(s: String){}
}

class Example : JClass {
    constructor() : super()

    private var obj: JClass? = this

    init {
        {
            obj?.test("1")
        }()

        object {
            fun run () {
                obj?.test("2")//Error:(17, 17) Kotlin: Expression is inaccessible from a nested class '<no name provided>', use 'inner' keyword to make the class inner
            }
        }.run()

        class A {
            fun run () {
                obj?.test("3")//Error:(23, 17) Kotlin: Expression is inaccessible from a nested class 'A', use 'inner' keyword to make the class inner
            }
        }
        A().run()
    }
}
