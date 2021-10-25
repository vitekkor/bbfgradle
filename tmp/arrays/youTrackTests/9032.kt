// Original bug: KT-17080

typealias Runnable = () -> Unit
typealias Consumer<T> = (T) -> Unit
typealias BiConsumer<T, U> = (T, U) -> Unit
typealias Function<T, R> = (T) -> R
typealias BiFunction<T, U, R> = (T, U) -> R
typealias Predicate<T> = (T) -> Boolean
typealias BiPredicate<T, U> = (T, U) -> Boolean
typealias Supplier<T> = () -> T
typealias UnaryOperator<T> = (T) -> T

