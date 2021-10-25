// Original bug: KT-15538

package bug

import java.lang.reflect.*

data class Descriptor(val clazz: Class<*>, val modifiers: Int, val info: List<String>) {
	constructor(clazz: Class<*>, modifiers: Int, vararg info: List<String>) :
			this(clazz, modifiers, mutableListOf<String>().apply { info.forEach { this@apply.addAll(it) } })
}

private val AnnotatedElement.info: List<String>
	get() = getAnnotation(Info::class.java)?.values?.toList() ?: listOf<String>()

annotation class Info(val values: Array<String>)

/**
 * A simple abstraction of com.google.common.reflect.Invokable of Guava 20.0.
 * https://github.com/google/guava/blob/v20.0/guava/src/com/google/common/reflect/Invokable.java
 */
abstract class Invokable<T, R> : AccessibleObject(), Member, GenericDeclaration

val <T : AccessibleObject> T.descriptor: Descriptor
	get() = when (this) {
		is Invokable<*, *> ->
			Descriptor(declaringClass,
			           modifiers,
			           info,
			           declaringClass.info)
		else -> throw AssertionError()
	}
