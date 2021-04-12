// Original bug: KT-30947

abstract class Foo<T>(val v: T) {
  fun bar() {
    when (this) {
      is LongFoo -> println(this.intVal)
    }
  }
}

fun Foo<*>.baz() {
  when (this) {
    is LongFoo -> println(this.intVal)
  }
}

val <T: Number> Foo<T>.intVal: Int
  get() = v.toInt()

class LongFoo(v: Long): Foo<Long>(v)
