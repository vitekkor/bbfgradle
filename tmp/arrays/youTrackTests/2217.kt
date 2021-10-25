// Original bug: KT-32002

inline class Id(val value: String)

fun main() {
    println(Id("abcd").equals(getX()?.id)) //false
    println(Id("abcd") == getX()?.id) //true

    val id = getX()?.id
    println(Id("abcd").equals(id)) //true
}

fun getX(): X? {
    return object : X {
        override val id: Id
            get() = Id("abcd")

    }
}

interface X {
    val id: Id
}
