// Original bug: KT-40045

fun <T : Any, R : T> assign(dest: R, vararg src: T?): R = null as R

class DIV(val tabIndex: String)

fun <T: Any> jsObject(builder: T.() -> Unit): T = null as T

fun foo(x: DIV) {
    assign(x, jsObject { tabIndex }) // tabIndex is resolved in OI and unresolved in NI because T is inferred to Any instead of DIV
}
