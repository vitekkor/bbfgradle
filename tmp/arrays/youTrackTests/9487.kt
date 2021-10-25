// Original bug: KT-6611

fun <T> allTypes(clazz : Class<T>) : Set<Class<Any>>
          = (listOf(clazz as Class<Any>) + (listOf(clazz.getSuperclass()) + clazz.getInterfaces().toList()).filterNotNull().flatMap { allTypes(it as Class<Any>) }).toSet()
