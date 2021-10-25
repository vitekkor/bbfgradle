// Original bug: KT-33975

class Tester {
    internal external fun foo(): Tester
    internal external fun bar(intSupplier: Function0<String>): Tester?

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Tester().foo().foo().bar {
                println("xyz")
                if (Math.random() > 0.5) {
                    return@bar "foo"
                }
                "bar"
            }
        }
    }
}
