// Original bug: KT-21908

sealed class Bird {
    open fun fly() {}
}

final class Penguin : Bird()
final class Ostrich : Bird() 
final class Kiwi : Bird()

fun <T: Bird> useInstanceInSealedHeirarchy(value: T) {
    val v = when(value) {
        is Penguin -> "Snow sledding on your belly sounds fun"
        is Ostrich -> "ostentatious and rich"
        is Kiwi -> "kiwiwiwiwi"
        
        else -> "Why is this branch necessary?"
    }
}
