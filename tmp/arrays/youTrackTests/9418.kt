// Original bug: KT-12751

class B {
    fun remove(charSequence: CharSequence) {
    }
}

fun foo(list: List<CharSequence>, b: B) {
    list.forEach(b::remove) // type inference failed
    list.forEach<CharSequence>(b::remove) // OK
}
