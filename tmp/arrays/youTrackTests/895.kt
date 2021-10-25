// Original bug: KT-44800

@Deprecated("asdf", ReplaceWith("this.bar(t, assertionCreator)"))
inline fun <T> A.foo(
    t: T,
    noinline assertionCreator: E<T>.() -> Unit
                    ) = 1
inline fun <T> A.bar(
    t: T,
    noinline assertionCreator: E<T>.() -> Unit
                    ) = 1

fun test(){
    A().foo("a") { } // apply suggestion results in the following:

    /*
        A().bar("a", fun E<String>.() {
            // unnecessary anonymous function
        })
     */
}

class A
class E<T>
