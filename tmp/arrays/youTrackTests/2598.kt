// Original bug: KT-39588

import kotlin.reflect.KProperty
class OptionDescriptor<T>
interface ArgumentValueDelegate<T> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = 123 as T
}
abstract class CLIEntity<TResult> constructor(val delegate: ArgumentValueDelegate<TResult>) {
    operator fun provideDelegate(thisRef: Any?, prop: KProperty<*>): ArgumentValueDelegate<TResult> = delegate
}
abstract class AbstractSingleOption<T : Any, TResult> constructor(delegate: ArgumentValueDelegate<TResult>) : CLIEntity<TResult>(delegate)
class ArgumentSingleNullableValue<T : Any>(descriptor: OptionDescriptor<T>): ArgumentValueDelegate<T?>
class SingleNullableOption<T : Any> constructor(descriptor: OptionDescriptor<T>) : AbstractSingleOption<T, T?>(ArgumentSingleNullableValue(descriptor))
fun main() {
    val x: List<Any>? by SingleNullableOption(OptionDescriptor()) // DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE
}
