// Original bug: KT-21523

import kotlin.reflect.KClass

interface Flag

annotation class FlagValue(val klass: KClass<out Flag>)

abstract class Outer {
    enum class MyFlag(val value: Int) : Flag {
        foo(0),
        bar(1)
    }

    @FlagValue(MyFlag::class)
    class myFlag
}

fun main(args : Array<String>) {
    val outerClass = Outer::class
    for (innerClass in outerClass.nestedClasses) {
        System.out.println(innerClass.simpleName)
    }
}
