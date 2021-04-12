// Original bug: KT-41846

interface Identifiable {
    val id: Any
}

interface StrIdentifiable : Identifiable {
    override val id: String
}

abstract class MyObject : StrIdentifiable {
}
