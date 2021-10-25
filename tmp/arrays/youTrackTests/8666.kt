// Original bug: KT-19887

open class OpenType

interface Marker

class MarkedType : OpenType(), Marker

fun <T> T.extention() where T : OpenType, T : Marker = println("1234")

fun <T> test1(obj: T) where T : OpenType, T : Marker {
	obj.extention() // OK
}

fun test2(obj: OpenType) {
	when (obj) {
		is Marker -> obj.extention() // doesn't compile
	}
}

fun test3(obj: Any) {
	if (obj is OpenType && obj is Marker) {
		obj.extention() // doesn't compile
	}
}
