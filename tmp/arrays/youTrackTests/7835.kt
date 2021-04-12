// Original bug: KT-25069

package uk.co.reecedunn.intellij.plugin.core
@Retention(AnnotationRetention.RUNTIME)
annotation class XQueryFunction(val function: String, val arity: Int = 0)
