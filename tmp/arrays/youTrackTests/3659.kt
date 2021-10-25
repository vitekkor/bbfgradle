// Original bug: KT-12455

fun bar(message: String, func: (MyClass, String) -> Unit) {
    val myObj = MyClass()
    func(myObj, message)
}

//And given the following class
class MyClass {

    fun print(value: String) {
        println(value.toUpperCase())
    }

}
