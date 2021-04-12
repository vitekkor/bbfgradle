// Original bug: KT-33310

class ClashedName

open class Base(val a: Any?) {
    class ClashedName
}

class Derived : Base(ClashedName()) {   // 1
    val base = Base(ClashedName())      // 2
}
