// Original bug: KT-23907

interface IBase  {
    val animal: IAnimal
}

interface IA : IBase
interface IB : IBase
interface IC : IBase

interface ID : IBase

interface IAnimal
interface IFox : IAnimal
interface IDog : IAnimal

fun foo(x: IBase): String {
    // (*)
    if (x is IA) return "A"
    else if (x is IB) return "B"
    else if (x is IC) return "C"
 
    // (**)
    val animal = x.animal
    return when (animal) {
        is IFox -> "Fox"
        is IDog -> "Dog"
        else -> "other kind of animal"
    }
}
