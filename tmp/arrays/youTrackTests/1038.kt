// Original bug: KT-34392

fun test() {
    var list = listOf(1)
    list += 1 // '+=' create new list under the hood ("Augmented assignment creates a new collection under the hood")
}
