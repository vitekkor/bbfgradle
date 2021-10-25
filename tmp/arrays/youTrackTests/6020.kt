// Original bug: KT-31520

// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_PARAMETER

class Out<out T>
interface Box<out R>

fun <R> choose(c: Out<Box<R>>) {}

fun f(l: Out<Box<*>>) {
    choose(l) // Not enough information to infer type variable R
}
