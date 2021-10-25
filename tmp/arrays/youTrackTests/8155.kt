// Original bug: KT-10532

abstract class DerivedAbstract : C.Base() {
    override abstract fun m()
}

public class C {
    open class Base () {
        open fun m() {}
    }


    companion object : DerivedAbstract() {
        override fun m() {}
    }
}
