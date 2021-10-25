// Original bug: KT-30575

object Delegate {
    operator fun getValue(thisRef: Any?, kp: Any?) = 1
    operator fun setValue(thisRef: Any?, kp: Any?, newValue: Int) {}
}

var testVar =
    Delegate

var testDelegatedVar by
Delegate
