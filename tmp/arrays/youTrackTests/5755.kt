// Original bug: KT-25222

package a.b.c

annotation class HelloWorld

class Foo {
    @HelloWorld
    fun bar() {}
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val clazz = Class.forName("a.b.c.Foo")
        val bar = clazz.declaredMethods.first { it.name == "bar" }
        val ann = bar.annotations.first()

        //Breakpoint!
        val a = 5
    }
}
