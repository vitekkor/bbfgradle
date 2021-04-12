// Original bug: KT-8711

package lab

fun main(args: Array<String>) {
   val xs = arrayOf(11,22,33)
   println("xs = ${xs.javaClass}")
	
   val ys = xs.reverse()
   println("ys = ${ys.javaClass}")
}

/*
Outputs:

xs = class [Ljava.lang.Integer;
ys = class java.util.ArrayList
*/
