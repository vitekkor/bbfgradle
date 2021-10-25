// Original bug: KT-30423

class A // "move `A` to separate file" exists
class Foo {  // "move `Foo` to separate file" exists
    class Bar // "move `Bar` to separate file" missing
}
