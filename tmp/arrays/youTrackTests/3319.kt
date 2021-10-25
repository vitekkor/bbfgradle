// Original bug: KT-7205

fun case2() {
    C.V() // to (2)
    C.Companion.V() // to (1)
}

open class C(){
    companion object  {
        class V //(1)
    }

    object V : L() 

}

open class L {
    operator fun invoke() {} //(2)
}
