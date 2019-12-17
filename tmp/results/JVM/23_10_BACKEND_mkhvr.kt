open class C(a: String)

fun box() {
    val a = ""
    
    open class B : C(a)
    class A : B() {
        init {
            A()
        }
    }
}
