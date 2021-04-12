// Original bug: KT-30283

// Kotlin
interface I
class C
open class OC
enum class E

fun iList(list: List<I>) {}
fun iList(): List<I> = listOf()

fun cList(list: List<C>) {}
fun cList(): List<C> = listOf()

fun ocList(list: List<OC>) {}
fun ocList(): List<OC> = listOf()

fun ecList(list: List<E>) {}
fun ecList(): List<E> = listOf()
