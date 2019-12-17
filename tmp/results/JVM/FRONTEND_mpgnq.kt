interface BK {
    fun foo()  = TODO()
}
interface KTrait: BK by TODO {
fun foo() = A().foo?.equals
class A : BK, KTrait
}