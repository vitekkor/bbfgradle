// Original bug: KT-27016

interface TestInterface{}

class TestClass : TestInterface{
	fun classMethod(){}
}

fun construct(): TestClass{
	return TestClass()
}

fun error(){
	val obj = construct()
	
	if (obj !is TestInterface){
		return
	}
	
	obj.classMethod()
}
