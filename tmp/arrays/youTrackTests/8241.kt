// Original bug: KT-14071

typealias MyArrayList = ArrayList<Any>

class N() : MyArrayList() {
    override fun add(element: Any): Boolean {
        if (!super<MyArrayList>.add(element)) {  // ERROR: Not an immediate supertype
            throw Exception()
        }
        return false
    }
}
