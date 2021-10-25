// Original bug: KT-23377

data class SomeClass(val someBoolean: Boolean)

fun someFunction(nullable: SomeClass?, nonNullable: SomeClass) {
    if (nullable?.someBoolean == true || nonNullable.someBoolean == true) {
        // ...
    }
}
