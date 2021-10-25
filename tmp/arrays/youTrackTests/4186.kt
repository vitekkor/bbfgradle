// Original bug: KT-36279

fun <T> allConditionalPredicates(subject: T, conditionalPredicates: Map<() -> Boolean, (T) -> Boolean>) = 
    conditionalPredicates
        .filter { it.key() }
        .all { it.value(subject) }
