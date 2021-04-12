// Original bug: KT-33914

object D1 {
    operator fun getValue(thisRef: Any?, kProp: Any?) = 42
    operator fun provideDelegate(thisRef: Any?, kProp: Any?) = D2
} 

object D2 {
    operator fun getValue(thisRef: Any?, kProp: Any?) = "meh"
}

val test by D1
