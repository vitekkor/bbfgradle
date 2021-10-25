// Original bug: KT-40208

object DemoKt {
    @JvmStatic
    fun main(args: Array<String>) {
        // https://github.com/demo -- link here
        run("https://github.com/demo/") // -- no link
    }

    fun run(x: String) {
    }
}
