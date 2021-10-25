// Original bug: KT-4177

class Failure<Elem, T>() {
    fun <U> map(): Failure<Elem, U> {
        val r = Failure<Elem, U>()
        return r // Error: Required 'Failure<Elem, U>' found: 'Failure<Elem, U>'
    }
}
