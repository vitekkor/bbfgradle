// Original bug: KT-27729

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

fun <R, T> bar(prop: ReadOnlyProperty<R, T>) {
    // ...
}

fun <R, T> foo(prop: ReadWriteProperty<R, T>) {
    bar(prop) // Compilation error: Type mismatch: inferred type is ReadWriteProperty<R, T> but ReadOnlyProperty<???, ???> was expected
}
