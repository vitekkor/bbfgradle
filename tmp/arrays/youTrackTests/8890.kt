// Original bug: KT-17877

val (() -> Unit).as2: FWrapper2
	get() = FWrapper2(this)

data class FWrapper2(private val inner: ()->Unit) : (Any?, Any?) -> Unit {
	override operator fun invoke(p1: Any?, p2: Any?) {
		inner()
	}
}
