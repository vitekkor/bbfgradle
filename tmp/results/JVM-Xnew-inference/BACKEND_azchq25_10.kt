open class C(
 grandParentProp: String
)
fun box()  {
    var sideEffects: String
val justForUsageInParentClosure = ""
open class B : C {
        val parentProp: String
constructor(): super(justForUsageInParentClosure
) {
            parentProp = ""
}
}
class A : B {
constructor(): super() {
sideEffects += A().run {}
        }
}
}