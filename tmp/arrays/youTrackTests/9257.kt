// Original bug: KT-15410

abstract class Foo protected constructor()

// warning "Protected function call from public-API inline function"
inline fun foo(f: () -> Unit) = object: Foo() {}
