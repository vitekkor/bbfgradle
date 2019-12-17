open class C(
 f: () -> Unit)
class B(var x: Int
)
fun B.foo() {
        object : C({x
}) {}.run {}
    }