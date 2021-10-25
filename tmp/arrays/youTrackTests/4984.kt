// Original bug: KT-28587

enum class Condition {

    EXACT {
        override fun evaluate(actual: Set<String>, expected: Set<String>) = actual == expected
    },

    ALL_OBJECTS_VALUES {
        override fun evaluate(actual: Set<String>, expected: Set<String>) = expected.containsAll(actual)
    };

    abstract fun evaluate(actual: Set<String>, expected: Set<String>): Boolean
}
