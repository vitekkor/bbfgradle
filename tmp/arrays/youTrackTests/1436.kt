// Original bug: KT-29640

open class Base {
    private suspend fun bug() {
    }
}

class Derived : Base() {
    private suspend fun bug() {
    }

    suspend fun callDerived() {
        bug()
    }
}

suspend fun main() {
    Derived().callDerived()
}
