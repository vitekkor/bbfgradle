// Original bug: KT-44536

package com.pvphero.utils.tools

object Test {
	
	@JvmStatic
	fun main(args: Array<String>) {
		val me: String? = null
		test(me)
		
		println()
		
		val comp: Any? = me
		test(comp)
	}
	
	inline fun <reified T> test(test: T?) {
		println(T::class)
		println(test is String)
		println(test is String?)
		println(test is Byte?)
	}
}
