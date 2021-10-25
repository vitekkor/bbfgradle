// Original bug: KT-20633

inline fun <reified E : Enum<E>> testReified(): HashMap<E,String> {
    println("stringThatDoesNotAppearAnywhereElseANDwasDeletedLaterBUTisStillPrinted")

    return hashMapOf<E,String>().apply { set(enumValues<E>()[0], "test") }
}

// in another kotlin file:

enum class Fruit {apple, banana, peach}

class Shop {
    val a = testReified<Fruit>()
}

fun main(args: Array<String>) {
    val shop = Shop()
    println(shop.a)
}
