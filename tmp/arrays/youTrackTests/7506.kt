// Original bug: KT-12027

public interface IntPredicate { public operator fun invoke(n : Int) : Boolean }

inline fun IntPredicate (crossinline f : (Int) -> Boolean) : IntPredicate {
	val impl = object : IntPredicate {
		override fun invoke(n: Int): Boolean = f(n)
	}
	return impl
}

fun processWithKotlinPredicate(numbers : IntArray, predicate : IntPredicate) : Long {
	var sum : Long = 0
	for (n in numbers)
		if (predicate(n))
			sum += n;
	return sum;
}

fun test() {
	val numbers = IntArray(10000000, { it -> it })
	val predicate = IntPredicate { it%2 == 0 }
	var result : Long = 0
	val nanos = kotlin.system.measureNanoTime { result = processWithKotlinPredicate(numbers, predicate) }
	println("($nanos ns) processWithKotlinPredicate = $result")
}

object Main {
	@JvmStatic fun main(args: Array<String>) {
		test()
	}
}
