// Original bug: KT-16727

import java.util.*

interface I {
    fun test() = object : ArrayList<String>() {}
}

class C : I

fun main(args: Array<String>) {
    val testAnonimousClass = C().test().javaClass
    println(testAnonimousClass.enclosingClass.canonicalName)//I.DefaultImpls => OK
    println(testAnonimousClass.name) //I$test$1 =>KO! (should be I.DefaultImpls$test$1
    //java.lang.Class#getSimpleBinaryName (used by #getSimpleName) does this :
    //getName().substring(enclosingClass.getName().length()) and the result should start with $ to be valid
    testAnonimousClass.simpleName //throw InternalError("Malformed class name")
}
