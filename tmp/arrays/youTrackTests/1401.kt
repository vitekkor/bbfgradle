// Original bug: KT-35116

enum class MyEnum {

    MyValue,
}

fun main() {
    try {
        MyEnum.valueOf("valueOf, do you really throw IAE?")
    } catch (iae: IllegalArgumentException) {
        println("yep, IAE")
    } catch (t: Throwable) {
        println(t)
    }
}
