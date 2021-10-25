// Original bug: KT-13835

typealias MyString = String
class Container<out T>(val x: T)
typealias MyStringContainer = Container<MyString?>

val msc = MyStringContainer(null) // null cannot be a value of not-null type MyString? /* = String */
val s = Container("").x!!         // Unnecessary
