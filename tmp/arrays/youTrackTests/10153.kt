// Original bug: KT-7874

public class A {
    public class Nested {
        companion object {
            fun f() {
                print("".foo)
            }
        }
    }

    companion object {
        private val String.foo: Int
            get() = 1
    }
}

fun main(args: Array<String>) {
    A.Nested.f()
}
