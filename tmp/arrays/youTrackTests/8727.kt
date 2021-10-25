// Original bug: KT-19202

@Deprecated("", ReplaceWith("NewClass"))
class OldClass @Deprecated("", ReplaceWith("NewClass(12)")) constructor()

class NewClass(p: Int = 0)

typealias Old = OldClass // <-- Apply quick fix here 

val a = Old()
