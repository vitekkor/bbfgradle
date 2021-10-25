// Original bug: KT-45607

interface Animal {
	fun speak()
}

class Cat : Animal {
	override fun speak() {
		println("Meow")
	}
}

class Dog : Animal {
	override fun speak() {
		println("Woof")
	}
}

inline fun <reified T : Animal> speak(animal: T) {
	animal.speak()
}

fun main() {
	val cat = Cat()
	speak(cat)
	val dog = Dog()
	speak(dog)
}
