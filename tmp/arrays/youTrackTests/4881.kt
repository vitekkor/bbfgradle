// Original bug: KT-32348

class myClass {

    fun myFun(age: Int, name: String = "name") {
        println("funWithDefaultValues")

    }

    fun myFun(age: Int) {
        println("funWithoutDefaultValues")
    }
}

fun main() {
    val myClass = myClass();
    myClass.myFun(10)
}
