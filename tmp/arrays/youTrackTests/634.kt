// Original bug: KT-41009

fun main()
{
	val one = Array<Fn>(150_000_000) { { i++ } }
	val two = Array<Fn>(150_000_000)
	{
		if ((it and 1) == 0) ({ i++ }) else ({ i-- })
	}

	println("round one")

	for (n in 1..4)
	{
		println("run $n")
		one.run()
	}

	println("\nround two")
	for (n in 1..4)
	{
		println("run $n")
		two.run()
	}

	println("\nround one (again)")
	for (n in 1..4)
	{
		println("run $n")
		one.run()
	}
}

typealias Fn = () -> Unit

var i = 0
fun Array<Fn>.run()
{
	for (n in 0..5)
	{
		val time = System.currentTimeMillis()
		for (fn in this)
			fn()
		println(System.currentTimeMillis() - time)
	}
}
