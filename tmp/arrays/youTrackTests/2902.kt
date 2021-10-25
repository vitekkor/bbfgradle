// Original bug: KT-22254

inline fun forEach(p:(Int)->Unit) {
	for(i in 1..3) p(i)
}
fun main(par:Array<String>){
	a@ fun f(){
		b@ forEach(
	   		c@ { p:Int ->
				print(p)
				return@a	// NoClassDefFoundError, #1
//				return@b	//	- internal error, #2
			}
		)
	}
	f()						// 123
}
