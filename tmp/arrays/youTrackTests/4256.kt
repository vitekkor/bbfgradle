// Original bug: KT-37092

class TestClass(id: Int, name: String) : Base() {  // <<-- 'id' value not passes to base class, unused field
    var name: String? = name

}

open class Base(var id: Int? = null)
