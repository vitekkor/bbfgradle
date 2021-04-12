// Original bug: KT-3285

class NoiseMaker {
    fun say(str: String) = println(str)
}

fun noiseMaker(f: NoiseMaker.() -> Unit) {
    val noiseMaker = NoiseMaker()
    noiseMaker.f()
}

abstract class Pet {
    fun <T> NoiseMaker.playWith(friend: T) {
        say("Yay! I'm playing with "+friend)
    }

    abstract fun play(): Unit
}

class Doggy(): Pet()  {
    override fun play() = noiseMaker {
        say("Time to play!")
        playWith("my owner")        //<<< This complains 'A receiver of type NoiseMaker' is required
    }
}
