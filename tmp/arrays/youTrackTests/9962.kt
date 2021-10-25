// Original bug: KT-9462

class Foo<T:Any>() {
    fun x() : T? = null
}

fun bar(f : Foo<*>) {
    val y = f.x()
    if (y != null) { // false warning here.
        print("Hey")
    }
}
