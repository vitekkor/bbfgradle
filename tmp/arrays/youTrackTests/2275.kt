// Original bug: KT-41654

       import org.jetbrains.annotations.*
      
       annotation class Foo(@NonNls val value: String, @NonNls val value2: String = "test")

       annotation class Foos(vararg val value: Foo = [])

       @Foo("Single param")
       class X

       @Foo(value = "Named param", value2 = "Named param2")
       class Y

       @Foos(Foo("Nested"))
       class Z

       @Foos(value = [Foo("Nested named")])
       class T
