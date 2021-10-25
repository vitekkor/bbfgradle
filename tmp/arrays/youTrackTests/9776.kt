// Original bug: KT-9070

fun <T> foo(v: T, u: T): T = v

fun bar() {
    // type mismatch on function literal: inferred type is '() -> Int', but 'Int' was expected
    foo(1, { 2 })
    // the same error
    1 ?: { 2 }
}
