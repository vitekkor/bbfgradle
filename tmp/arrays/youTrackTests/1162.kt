// Original bug: KT-44546

interface Some {
    fun method(): Unit
}
fun <S> elvis(nullable: S?, notNullable: S): S = TODO()
fun <R : Some> Some.doWithPredicate(predicate: (R) -> Unit): R? = TODO()

fun test(derived: Some) {
    val expected: Some = derived.doWithPredicate { it.method() } ?: TODO()
    val expected2: Some = elvis(derived.doWithPredicate { it.method() }, TODO()) // false positive unresolved reference on `method` call (we can infer variable into non-Nothing)
}
