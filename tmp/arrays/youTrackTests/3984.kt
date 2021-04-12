// Original bug: KT-25656

annotation class MyAnnotation(
    val nested: NestedAnnotation = NestedAnnotation(
        intArray = [1, 2, 3] // Type inference failed. Expected type mismatch: inferred type is Array<Int> but IntArray was expected
    )
)

annotation class NestedAnnotation(
    val intArray: IntArray
)
