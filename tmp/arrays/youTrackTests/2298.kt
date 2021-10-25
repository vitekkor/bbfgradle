// Original bug: KT-42055

data class Person(val name: String = "untitled", val age: Int = 20)

fun factory(cstr: ()->Person) : Person {
    return cstr()
}

fun invokeFactory():Person {
    return factory(::Person)
}
