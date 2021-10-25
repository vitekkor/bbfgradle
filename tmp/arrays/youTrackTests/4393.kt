// Original bug: KT-34779

class CountingString(private val s: String) : CharSequence {
    var lengthCtr = 0
    var getCtr = 0

    override val length: Int
    	get() = s.length.also { lengthCtr++ }
    override fun subSequence(startIndex: Int, endIndex: Int) = TODO("not implemented")
    override fun get(index: Int) = s.get(index).also { getCtr++ }

    fun reset() {
        lengthCtr = 0
        getCtr = 0
    }

    fun printCtrs() {
        println("getCtr: ${getCtr}")
        println("lengthCtr: ${lengthCtr}")
    }
}

fun main(args: Array<String>) {
    val cs = CountingString("abcd")

    // INCORRECT behavior
    for ((_, _) in cs.withIndex()) {}
    cs.printCtrs()
    cs.reset()

    // CORRECT behavior
    val cswi = cs.withIndex()
    for ((_, _) in cswi) {}
    cs.printCtrs()
    cs.reset()

    // Intended behavior of iterating over CharSequence
    val csit = cs.iterator()
    while (csit.hasNext()) {
        csit.next()
    }
    cs.printCtrs()
    cs.reset()    

    // CORRECT behavior when element variable is present in destructuring declaration
    for ((_, v) in cs.withIndex()) {}
    cs.printCtrs()
    cs.reset()
}
