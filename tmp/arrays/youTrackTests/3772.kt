// Original bug: KT-37128

inline fun <reified T> T.causeBug() {}

interface SomeToImplement<SELF_TVAR>

interface SomeInterface<T> where T: SomeToImplement<T>

abstract class SomeChild<T>: SomeInterface<T> where T: SomeToImplement<T> {
  fun op() = causeBug()
}
