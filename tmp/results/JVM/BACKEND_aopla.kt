import kotlin.reflect.KProperty
class Delegate<T>(
 f: () -> T) {
    operator fun getValue( thisRef: Any?,property: KProperty<*>
): T = TODO()
}
val localL by Delegate {
when (false) {
 44L === null -> {}
}
 }