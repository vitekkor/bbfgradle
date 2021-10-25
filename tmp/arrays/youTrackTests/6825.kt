// Original bug: KT-8449

class A(val x: Int) {
    companion object {
        fun A.f(): Int = x // Error:(3, 25) Expression is inaccessible from a nested class 'Companion', use 'inner' keyword to make the class inner
    }
}
