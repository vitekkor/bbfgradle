// Original bug: KT-30064

class Test1 {
	fun test(test: String) = Unit
}

class Test2 {
	fun test(test: InlineString) = Unit // disappeared in annotation processing
}

inline class InlineString(val value: String)
