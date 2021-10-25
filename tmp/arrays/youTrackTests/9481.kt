// Original bug: KT-13224

abstract class Model: ArrayList<String>()

class PersonModel : Model() {
    fun test() = this.forEach { println(it) } // Stackoverflow error
    fun sizeTest() = println(size) // Stackoverflow error
}