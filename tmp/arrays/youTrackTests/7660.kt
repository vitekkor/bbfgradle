// Original bug: KT-26340

var foo: Int
    get() {
        println("property getter")
        return 5
    }
    set(value) {
        println("property setter")
    }

fun setFoo(a: Int): Int {
    println("function setFoo")
    return 5
}

fun getFoo(): Boolean {
    println("function getFoo")
    return true
}

fun main(args: Array<String>) {
    foo
    foo = 5
    getFoo()
    setFoo(5)
}
