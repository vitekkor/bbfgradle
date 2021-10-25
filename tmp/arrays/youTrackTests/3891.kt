// Original bug: KT-6822

// type of a: (Int?) -> Int? but must be (Int?) -> Int
val a = l@ { it: Int? ->
    if (it != null) return@l it // no smart cast 
    5
}

// type of b: (Int?) -> Int 
val b = l@ { it: Int? ->
    if (it != null) it // smart cast
    else 5
}
