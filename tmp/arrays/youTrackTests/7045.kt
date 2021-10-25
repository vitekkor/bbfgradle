// Original bug: KT-29081

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun executeOnceWithoutContracts(functionToRun: () -> Unit) {
    functionToRun()
}

//does not compile
/*
fun startGameV1(): Int {
    val lives: Int
    executeOnceWithoutContracts {
        lives = 5
    }
    // Compile error here
    return lives
}*/

@ExperimentalContracts
fun executeOnce(functionToRun: () -> Unit) {
    contract {
        callsInPlace(functionToRun, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    functionToRun()
}

//Code is ok buy may generate exception during compilation.

@ExperimentalContracts
fun startGameV2(): Int {
   val lives: Int
   executeOnce {
       lives = 5
   }
   // no compile error!
   return lives
}

@ExperimentalContracts
fun main() {
   startGameV2()
}