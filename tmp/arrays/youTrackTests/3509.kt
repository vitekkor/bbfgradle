// Original bug: KT-26919

package test

import java.lang.reflect.Modifier

internal class Test

fun main(args: Array<String>) {
    System.out.println(Modifier.isPublic(Test::class.java.modifiers))
}
