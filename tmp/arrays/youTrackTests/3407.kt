// Original bug: KT-38347

open class A{
    var arg :A1? = null
    
    open class A1{
        var a1Arg = 0
    }
}
class B : A(){
    
    open class B_A1 :A1(){}
    inline fun A.set(block:B_A1.()->Unit){
        val arg1 = B_A1()
        arg1.block()
        arg = arg1
    }
}
class C : A(){
    open class C_A1 :A1(){}
    inline fun A.set(block:C_A1.()->Unit){
        val arg1 = C_A1()
        arg1.block()
        arg = arg1
    }
}
fun b(block:B.()->Unit){

}
fun A.c(block:C.()->Unit){
}
fun main(){
    b {
        val tmpB = this
        c {
            set { 
                a1Arg = 1
            }
            set {  }
            this@b.set { }
            tmpB.set{ }
        }
    }
    
}
