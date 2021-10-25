// Original bug: KT-37844

import kotlin.random.Random

fun main() {
    var task = getNewThing() // "Variable 'task' is assigned but never accessed" in both IDEA and KotlinC
    if (Random.nextBoolean()) return // removing this line makes the warning go away
    while (true) { // removing this loop makes the warning go away
        println(task) // `task` is used here
        getNewThingMaybeNull().let { // changing the `let` to a plain assignment to a `tmp` variable makes the warning go away
            if (it == null) {
                while (true) { // executing this loop without the `if (it != null)` makes the warning go away, as does removing the loop. Changing the loop to while(false) makes no difference though
                    task = getNewThingMaybeNull() ?: return@main // commenting this line makes the warning go away
                    println(task) // `task` is also used here
                }
            } else {
                task = it
            }
        }
    }
}

class Thing

fun getNewThingMaybeNull(): Thing? {
    return if (Random.nextBoolean())
        null
    else
        Thing()
}
fun getNewThing(): Thing = Thing()
