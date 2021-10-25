// Original bug: KT-4744

interface FirstTrait
interface SecondTrait

fun <T> T.doSomething() where T: FirstTrait, T: SecondTrait {
    println("hello")
}

class Foo: FirstTrait, SecondTrait {
    fun bar() {
        doSomething()
    }
}
