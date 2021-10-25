// Original bug: KT-4788

fun main(args: Array<String>) {
    val s = listOf(1, 2)
    args.map {
        try {
            doSomething();
        } catch (e: Exception) {
            //Unit
        }

    }
}

fun doSomething() {

}
