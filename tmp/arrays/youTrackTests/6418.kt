// Original bug: KT-30254

sealed class Animal {
    object Dog : Animal()
    object Cat : Animal()

    companion object {
        val all =  setOf(Dog, Cat) 
    }
}

sealed class Fruit {
    object Apple : Fruit()
    object Mango : Fruit()

    companion object {
        val all get() = setOf(Apple, Mango) 
    }
}

fun main() {
    println(Animal.Dog)
    println(Animal.all)

    println(Fruit.Apple)
    println(Fruit.all)
}
