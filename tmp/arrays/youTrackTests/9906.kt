// Original bug: KT-10811

interface I<T>
class A<T>(value: T) : I<T>
class B<T> : I<T>

fun <T> fail() : B<T> = TODO()
fun fn() : I<String?> {
    return when("") {
        "a"-> fail()
        "b"-> fail()
        else -> A("") // adding type argument <String?> makes it work
    }
}
