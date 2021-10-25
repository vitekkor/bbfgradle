// Original bug: KT-14653

sealed class Lookup {

    object Miss : Lookup()

    class Hit(val value: String) : Lookup()
}

fun doLookup(query: String?): Lookup {
    if (query == null) return Lookup.Miss
    else return Lookup.Hit("success")
}
