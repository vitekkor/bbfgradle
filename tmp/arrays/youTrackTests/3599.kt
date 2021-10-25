// Original bug: KT-30962

suspend fun main() {
    f1()
    print("called f1")
}

suspend fun f1() {
    f2()
    print("called f2")
}

suspend fun f2() {
    f3()
    print("called f3")
}

suspend fun f3() {
    doSmth()
    print("done")
}

suspend fun doSmth() {
    //breakpoint here
    print("I'm doSmth")

}
