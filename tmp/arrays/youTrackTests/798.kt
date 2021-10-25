// Original bug: KT-44536

package com.pvphero.utils.tools

object Test {
	
	@JvmStatic
	fun main(args: Array<String>) {
		val me: String? = null
		val comp: Any? = me
		
		println(comp is String) // false
		println(comp is String?) // true
		println(comp is Byte?) // true
	}
}
