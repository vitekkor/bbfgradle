// Original bug: KT-26907

interface A {}
class B : A {}
class C : A {}
class D {
    fun foo(b: B): String = "text"
    
    fun goo() {
        listOf(B(), C()).mapNotNull {
            if (it is B) foo(it)
            else null
        }
    }
}
