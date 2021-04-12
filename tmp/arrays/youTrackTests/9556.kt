// Original bug: KT-12550

open class Base
class Derived: Base()

interface I {
    val foo: Collection<Base>
}

open class KotlinBase : I {
    override val foo: Collection<Derived> get() = emptyList()
}

// OK
class KotlinDerived : KotlinBase()

// getFoo in KotilnBase clashes with getFoo in I, incompatible return type
// public class JavaDerived extends KotlinBase { }
