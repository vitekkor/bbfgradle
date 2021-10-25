// Original bug: KT-39817

operator fun Int.invoke(){}
val foo = 0; var foo1 = 0; const val foo2 = 0
val bar = 1; var bar1 = 1; const val bar2 = 1
val baz = 2; var baz1 = 2; const val baz2 = 2
fun test(){
	foo(); foo1(); foo2()
	bar(); bar1(); bar2()
	baz(); baz1(); baz2()
}
