// Original bug: KT-9555

//file 1
open class A {
    @JvmField public val publicField = "1";
    @JvmField internal val internalField = "2";
    @JvmField protected val protectedfield = "3";
}

open class B : A() {

}

//file 2
open class C : B() {
    fun test(): String {
        return super.publicField + super.internalField + super.protectedfield
    }
}

fun main(args: Array<String>) {
    C().test()
}
