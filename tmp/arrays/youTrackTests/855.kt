// Original bug: KT-44959

class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()

    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
