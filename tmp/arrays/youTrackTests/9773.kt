// Original bug: KT-4147

open class A {
    open val p = 1;
}

open class B : A() {

}

class C : B() {
    fun a2() {
        p
       //ALOAD 0
       //INVOKEVIRTUAL calls/A.getP ()I
       //seems there should be C.getP()I
    }
}
