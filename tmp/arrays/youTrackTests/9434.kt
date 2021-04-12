// Original bug: KT-13848

class Forest(val numTrees:Int?)

fun doSth(forest: Forest?) {
	forest?.numTrees ?: throw IllegalArgumentException("No Trees")

	println("Some print 1")
	println("Some print 2")

}

fun main(args: Array<String>) {
	doSth(Forest(3))
	doSth(Forest(null))
}
