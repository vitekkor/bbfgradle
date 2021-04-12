// Original bug: KT-27221

sealed class Dog
sealed class Mongrel : Dog()
sealed class GoodMongrel : Mongrel()
object BadMongrel : Mongrel()
object Laika : GoodMongrel()
object Azor : GoodMongrel()

data class Car(
    val dog: Mongrel
)

fun example(car: Car) {
    val dog: Dog
    dog = car.dog
    when (dog) {
        is GoodMongrel -> {
            val a = when (dog) { // does not compile
                is Laika -> "ÐÐ°Ð¹ÐºÐ°"
                is Azor -> "Azor"
            }
        }
        else -> TODO()
    }
}

fun example2(car: Car) {
    val dog: Dog
    dog = car.dog
    when (dog) {
        is GoodMongrel -> {
            val a = when (dog as GoodMongrel) { // does compile but warns that cast is redundant
                is Laika -> "ÐÐ°Ð¹ÐºÐ°"
                is Azor -> "Azor"
            }
        }
        else -> TODO()
    }
}
