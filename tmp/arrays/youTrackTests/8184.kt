// Original bug: KT-22840

fun main(par:Array<String>){
    m1@  // label on separate line IS ATTACHED to subsequent expression


    for (i in 1..3) {
	print(i)
	for (j in 1..3) {
		print(j)
                break@m1
    }
    } // 11
}
