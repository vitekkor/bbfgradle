// Original bug: KT-19879

fun test() {

    val someThing: Any? = null
    val otherThing: Any? = null

    if (!((someThing?.equals(otherThing) ?: otherThing) == null)) {
        // someThing and otherThing are NOT both null and NOT equal (as signified by the negation operator)
    }
}
