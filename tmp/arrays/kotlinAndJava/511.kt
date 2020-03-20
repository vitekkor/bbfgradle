//File Level.java
import kotlin.Metadata;

public enum Level {
   O,
   K;
}


//File Main.kt
// IGNORE_BACKEND: JVM_IR

private fun Any?.doTheThing(): String {
    when (this) {
        is String -> return this
        is Level -> {
            when (this) {
                Level.O -> return Level.O.name
                Level.K -> return Level.K.name
            }
        }

        else -> return "fail"
    }
}


fun box(): String {
    return "O".doTheThing() + Level.K.doTheThing()
}

// 1 TABLESWITCH

