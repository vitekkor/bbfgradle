// Original bug: KT-15533

sealed class C0<in T0> {
    // 'T1' is invariant but used for the contravariant parameter to 'C0'.
    // This would seem to be unsafe because 'when' and smart casts can be
    // used to refine a value of type 'C0<_>' to one of type 'C1<_>' thus
    // exposing 'value'.
    class C1<T1>(val value: T1) : C0<T1>()
}

fun main(args: Array<String>) {
    // This type signature is necessary for the 'ClassCastException' to occur.
    // The actual type of 'x' is 'C0.C1<String>' yet Kotlin is happy to assign
    // it 'C0<Boolean>'.
    val x: C0<Boolean> = C0.C1("Not a Boolean!")
    
    when(x) {
        is C0.C1 -> {
            // This throws 'java.lang.ClassCastException' when it attemps to
            // cast 'x.value' to 'java.lang.Boolean'.
        	println(x.value)
        }
    }
}
