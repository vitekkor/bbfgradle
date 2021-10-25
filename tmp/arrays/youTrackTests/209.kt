// Original bug: KT-20662

interface IFoo

class FooImpl(val z: Any) : IFoo

object Test15 : IFoo by Test15          // Error (reference to definitely uninitialized singleton in interface delegate initializer)

object Test16 : IFoo by FooImpl(Test16) // Error (reference to definitely uninitialized singleton in interface delegate initializer)

object Test17 : IFoo by object : IFoo { val test = Test17 } // Error (reference to definitely uninitialized singleton in interface delegate initializer)
