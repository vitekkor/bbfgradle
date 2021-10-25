// Original bug: KT-24111

var deadline = 10

tailrec fun recursionTest() {
    if (deadline-- == 0) return

    run {
        println("$deadline")
        return recursionTest()
    }
}
