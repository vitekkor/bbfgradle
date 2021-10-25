// Original bug: KT-22839

inline fun f(noinline p:()->Unit) {		// noinline parameter
	p()
	println("A")
}
val v = {print(1)}
fun main(par:Array<String>){
	f(v)								// 1A - INLINED
	f(v)								// 1A - INLINED
}
