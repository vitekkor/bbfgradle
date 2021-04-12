// Original bug: KT-21526

import TestClass.NamedObject.CONST

class TestClass{
	fun method(){
		print(CONST)
	}
	
	private object NamedObject{
		const val CONST = "abc"
	}
}

fun main(args: Array<String>){
	TestClass().method()
}
