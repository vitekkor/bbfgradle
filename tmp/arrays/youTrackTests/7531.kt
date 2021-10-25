// Original bug: KT-21812

fun main(args: Array<String>) {
    args?.let {
        object {
            init {
                println(args.toString())
            }
        }
    }
}
