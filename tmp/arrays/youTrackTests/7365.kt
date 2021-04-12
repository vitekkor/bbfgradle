// Original bug: KT-27439

class Foo {
    // Name in the bytecode: `bar$main` (where 'main' is the module name)
    internal fun bar() {}
}

// Name in the bytecode: `Baz$main`?..
internal class Baz
