// Original bug: KT-21973

import java.lang.reflect.Modifier
import kotlin.reflect.jvm.kotlinFunction

fun loadByReflection(){
    val classLoader = ::loadByReflection.javaClass.classLoader
    val clazz = classLoader.loadClass("SubjectKt")
    val methods = clazz.methods
    println(methods.filter { Modifier.isStatic(it.modifiers) }.map { it.kotlinFunction })
}

fun main(args: Array<String>) {
    loadByReflection()
}
