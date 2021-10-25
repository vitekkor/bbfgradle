// Original bug: KT-22891

inline fun i(p:()->Unit) {p()}
val v = { println(1) }			// reference v - lambda
fun f() = i(v)					// lambda passed as reference - not inlined
fun main(par:Array<String>){
	f()	// 1
}
