// Original bug: KT-20180

sealed class Foo {
    class Bar : Foo() // it's OK
}
// cannot access '<init>': it is private in 'Foo'
// this type is sealed, so it can be inherited by only its own nested classes or objects
class Baz : Foo() 
