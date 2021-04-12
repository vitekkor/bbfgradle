// Original bug: KT-34830

class Foo<A>
{
	fun foo(a: A?)
	{
	}
}

class Bar<A>
{
	fun foo(a: A)
	{
	}
}

fun <A> Bar<A>.foo(a: A?)
{
}

fun <A> Bar<A>.bar(a: A?)
{
}

data class Quux<A>(private val foo: (A) -> Unit)
data class QuuxWithNull<A>(private val foo: (A?) -> Unit)

fun <A> fooQuux(foo: Foo<A>): QuuxWithNull<A>
{
	return QuuxWithNull(foo::foo) // OK, does not use extension method
}

fun <A> barQuux(bar: Bar<A>): Quux<A>
{
	return Quux(bar::foo) // OK, does not use extension method
}

fun <A, B> barFuncWithNull(bar: Bar<A>): (A?) -> Unit
{
	return bar::foo // OK, doesn't use extension method to infer types, I guess
}

fun <A, B> barQuuxWithNullDifferentJVM(bar: Bar<A>): QuuxWithNull<A>
{
	return QuuxWithNull(bar::bar) // OK, use extension method with different JVM signature
}

fun <A> barQuuxWithNullExplicit(bar: Bar<A>): QuuxWithNull<A>
{
	return QuuxWithNull<A>(bar::foo) // Ok, but "Remove explicit type arguments. Inspection Info: This inspection reports function calls with type arguments that can be safely removed and inferred."
}

fun <A, B> barQuuxWithNullImplicit(bar: Bar<A>): QuuxWithNull<A>
{
	return QuuxWithNull(bar::foo) // (!?) Type inference fails. Please try to specify type arguments explicitly.
}
