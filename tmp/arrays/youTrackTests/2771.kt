// Original bug: KT-40134

open class A{
    fun fA(){

    }
}

class B : A(){
    fun fB(){
    }
}

fun f(){
    val a = A();
    if(a !is B){
        return
    }
    a // call quick doc
}
