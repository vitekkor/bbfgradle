// Original bug: KT-19598

interface A
interface B

class C1 : A, B
class C2 : A, B

open class SomeClass(a: A)

class SomeOtherClass : SomeClass(if (true) C1() else C2())

fun main(args: Array<String>) {
    SomeOtherClass()
}
