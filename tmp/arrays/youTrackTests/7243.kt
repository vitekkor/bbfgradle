// Original bug: KT-28212

val a = object { 
    val B19 = object { // Warning: anonymous object member could be private[, because it can't be referenced from the outer scope]
        // ...
    }
}
