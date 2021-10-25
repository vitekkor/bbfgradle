// Original bug: KT-8689

fun main(args: Array<String>) {
    args.let { _ ->
        // Have to capture outer "args" var to cause this
        fun String.foo() = this + args
        fun String.bar() = this.foo()
    }
}
