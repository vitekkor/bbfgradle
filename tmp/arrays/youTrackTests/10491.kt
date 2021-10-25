// Original bug: KT-3536

class B{}
    fun <T> a(l : ArrayList<T>, t:T) : B { return B() }
    fun b(b : String?) {
        val x = when (b) {
            null -> null
            else -> a(ArrayList<String>(), b) // b is still 'String?' here
        }
    }
