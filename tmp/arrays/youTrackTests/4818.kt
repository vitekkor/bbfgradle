// Original bug: KT-33553

val t0 = "sd".plus("sdse").plus("sdsee")

class Tt {
	fun t1() = "sd".plus("sdse").plus("sdsee")

	fun t2() : String {
		return "sd".plus("sdse").plus("sdsee")
	}
}
