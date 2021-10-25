// Original bug: KT-10755

package kt_tests.intersectionTypes

interface IBase
interface IDerived : IBase
abstract class ABaseImpl : IBase
interface IAnimal : IDerived

class Cat : ABaseImpl(), IAnimal    
// Cat : IAnimal : IDerived
// Cat : ABaseImpl() : IBase

class Dog : ABaseImpl(), IAnimal    
// Dog : IAnimal : IDerived
// Dog : ABaseImpl() : IBase

fun test1(x1: Cat, x2: Dog): IDerived =
        if (true) {
            if (true) x1 else x2 // OK
        }
        else TODO()

fun test1a(x1: Cat, x2: Dog): IDerived =
        if (true) {
            if (true) {
                if (true) x1 else x2 // TYPE_MISMATCH
            }
            else x2 // OK
        }
        else TODO()

fun test2(x1: Cat, x2: Dog): IDerived =
        if (true) x1 else x2 // OK

fun <T> select(x1: T, x2: T): T = x1

val test3: IDerived = select(Cat(), Dog())
val test4: IDerived = select(select(Cat(), Dog()), TODO()) // TYPE_MISMATCH: required IDerived, found IBase
