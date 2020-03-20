//File Game.java
import kotlin.Metadata;

public enum Game {
   ROCK,
   PAPER,
   SCISSORS,
   LIZARD,
   SPOCK;
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import Game.*

fun box(): String {
    val a = arrayOf(LIZARD, SCISSORS, SPOCK, ROCK, PAPER)
    a.sort()
    val str = a.joinToString(" ")
    return if (str == "ROCK PAPER SCISSORS LIZARD SPOCK") "OK" else "Fail: $str"
}

