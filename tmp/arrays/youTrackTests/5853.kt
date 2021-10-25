// Original bug: KT-31345

sealed class Animal

object Dog : Animal()
object Cat : Animal()

fun buildAnimalList(
    dogCount: Int,
    catCount: Int
): List<Animal> = TODO()

class AnimalFactory {

    // no need to pass around the parameter definition
    // but "Named arguments are not allowed for function types"
    val buildAnimals = ::buildAnimalList

    // parameters need to be defined again
    // but named arguments are supported
    fun buildAnimals(
        dogCount: Int,
        catCount: Int
    ): List<Animal> = buildAnimalList(dogCount, catCount)

}
