// Original bug: KT-22756

fun main(par:Array<String>){
	class C (var i:Int) {
		operator fun plus(p:C):C {println("plus"); return C(i+p.i)}
		operator fun plusAssign(p:C):Unit {println("plusAssign"); i+=p.i}
	}
	val a = C(1)
	val b = C(2)
	a + b  				// plus
	a += b  			// plusAssign
	println(a.i)		// 3
}
