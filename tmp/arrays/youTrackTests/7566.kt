// Original bug: KT-10822

interface A {
    fun foo() {
        println("foooo")
    }
}

interface B : A {
    fun hoo() {
        println("hoooo")
    }
}

fun<T:A> doer(init:()->T) : T = init()

class Z {
    operator fun<T:A> invoke(init:Z.()->T) : T = init()
    infix fun<T:A> doer(init:Z.()->T) : T = init()

}

interface  ARoot<T> {
    val self : T
    infix fun<U:A> consume(init: T.() -> U) : U = self.init()
    operator fun<U:A> invoke(init:T.()->U) : U = self.init()
}

class Y : ARoot<Y> {
    override val self: Y
        get() = this
}

fun main(args: Array<String>) {
    doer {
        object : B {}
    }.hoo()
    val z = Z()
    val y = Y()
    z.doer { object : B {} }.hoo()
    y {
        z {
            object : B {}
        }
    }.hoo()

      y.consume {
        z {
            object : B {}
        }
    }.hoo()


    z {
        object : B {}
    }.foo()

    z.doer { object : B {} }.foo()
    z.doer { object : B {} }.hoo()

}
