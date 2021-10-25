// Original bug: KT-19399

class Foo {
    var bar = false

    inline fun ifNotBusyPerform(action: (complete: () -> Unit) -> Unit) {
        action.invoke {
            bar = false // <-- No crash if this line is commented out!
            println("complete callback executing")
        }
    }

    fun ifNotBusySayHello() {
        ifNotBusyPerform {
            println("Hello!")
        }
    }

    inline fun inlineFun(s: () -> Unit) {
        s()
    }

    fun start() {
        inlineFun {
            {
                ifNotBusyPerform {
                    ifNotBusySayHello()
                }
            }()
        }        
    }
}
fun main(args: Array<String>) {
    val foo = Foo()
    foo.start()
}

