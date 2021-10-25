// Original bug: KT-9294

fun foo(type: Class<*>) {
  // (incorrect?) warning: Condition 'type.superclass == null' is always 'false'
  // also can't use `.getSuperclass()` instead of `.superclass` (compile error: unresolved reference)
  // both problems disappear when changing `Class<*>` to `Class<T>`
  if (type.superclass == null) { 
    throw NullPointerException()
  }
}
