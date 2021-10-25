// Original bug: KT-12027

public interface IntPredicate { public operator fun invoke(n : Int) : Boolean }
inline fun IntPredicate (crossinline f : (Int) -> Boolean) : IntPredicate {
	val impl = object : IntPredicate {
		override fun invoke(n: Int): Boolean = f(n)
	}
	return impl
}
val predicate = IntPredicate { it%2 == 0 }
