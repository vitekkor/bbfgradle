// Original bug: KT-32014

class MyClass{
    val myList: List<Int> = ArrayList()

    fun canBeSolvedBy() {
        if(myList is ArrayList) {     // should be handled by smart casting
            myList.add(2)
        }
    }
}
