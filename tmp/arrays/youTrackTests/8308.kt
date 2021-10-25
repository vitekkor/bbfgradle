// Original bug: KT-22235

fun main(par:Array<String>){
    m@ for (i in 1..3) {		//	- label m
        m@ for (j in 1..3) {	//	- same label m
            break@m				//	- break which loop?
        }
    }
	m@ println(1)
	m@ println(2)
}
