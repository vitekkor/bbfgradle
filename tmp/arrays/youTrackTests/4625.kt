// Original bug: KT-19645

sealed class RootSealed

sealed class NestedSealed1 : RootSealed() {
	class Class1 : NestedSealed1()
	class Class2 : NestedSealed1()
}

sealed class NestedSealed2 : RootSealed() {
	class Class1 : NestedSealed2()
	class Class2 : NestedSealed2()
}

fun test1(obj: RootSealed): Int = when (obj) {
	is NestedSealed1.Class1 -> TODO()
	is NestedSealed1.Class2 -> TODO()
	is NestedSealed2.Class1 -> TODO()
	is NestedSealed2.Class2 -> TODO()
}

fun test2(obj: NestedSealed1): Int = when (obj) {
	is NestedSealed1.Class1 -> TODO()
	is NestedSealed1.Class2 -> TODO()
}
