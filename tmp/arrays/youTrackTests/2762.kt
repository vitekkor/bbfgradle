// Original bug: KT-23624

fun main(args: Array<String>) {
    print (test() === test()) //'true' in new behavior and 'false' in current
}

fun test() = foo { it.length }

inline fun foo(crossinline f: (String) -> Unit) =
    {
        f("123") 
    }
