// Original bug: KT-33310

open class Base(val x: Int) {
    companion object {
        val companionField: Int = 42
    }
}

class Derived : Base(companionField) // would've had to write 'Base(Base.companionField)' which is weird too
