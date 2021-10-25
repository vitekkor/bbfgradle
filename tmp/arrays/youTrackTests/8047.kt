// Original bug: KT-23448

fun <T> T.mapIf(condition: Boolean, mapper: (T) -> T): T = if (condition) mapper(this) else this

fun <T> T.mapIf(predicate: (T) -> Boolean): ((T) -> T) -> T = { mapper: (T) -> T -> mapIf(predicate(this)) { mapper(this) } }
