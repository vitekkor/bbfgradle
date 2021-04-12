// Original bug: KT-24247

	fun f() {
		val any = "" as Any
		when (any) {
			is String -> any.length + any.hashCode()
			is Int -> any + 1
		}
	}
	
	fun f2() {
		val any = "" as Any
		fun f() {
			val any2 = "" as Any
			when (any2) {
				is String -> any2.length + any2.hashCode()
				is Int -> any2 + 1
			}
			fun f() {
				val any3 = "" as Any
				when (any3) {
					is String -> any3.length + any3.hashCode()
					is Int -> any3 + 1
				}
			}
		}
		when (any) {
			is String -> any.length + any.hashCode()
			is Int -> any + 1
		}
	}
	
	fun f3() {
		val any = "" as Any
		when (any) {
			is String -> any.length + any.hashCode()
			is Int -> any + 1
		}
		fun f() {
			val x = "" as Any
		}
	}
	