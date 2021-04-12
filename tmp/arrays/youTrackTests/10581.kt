// Original bug: KT-3904

class C(val params : Map<String, String>) {}
open class B(private val handler: C.() -> Unit)
class A : B({
    val parentId = this.params["comment-parent-entity"] // remove "this" to fix
})
