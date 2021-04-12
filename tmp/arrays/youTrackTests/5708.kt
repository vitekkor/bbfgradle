// Original bug: KT-32014

class MyClass{
    private val _myList = ArrayList<Int>()
    val myList: List<Int> get() = _myList  // boilerplate as we use 2 params instead of just one

    fun foo(){
        _myList.add(1)
    }
}
