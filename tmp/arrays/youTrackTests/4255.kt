// Original bug: KT-37092

class TestClass : Base {
    var name: String? = null

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }
}

open class Base(var id: Int? = null)
