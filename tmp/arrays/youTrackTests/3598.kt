// Original bug: KT-30969

suspend fun main() {
    f()
}
suspend fun f() {
    val x = listOf(1, 2, 3, 4, 5)
    x.forEach {
        it.toString().doSomeExtensionSuspend()
        //breakpoint here
        it.toString().doSomeExtension()
    }
    print("done")
}

suspend fun String.doSomeExtensionSuspend() {
    print(this)
}

fun String.doSomeExtension() {
    print(this)
}
