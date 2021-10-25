// Original bug: KT-4399

fun main(args : Array<String>) {
    A().test()
}

class A {
    var value: Int = 0

    fun test(){
        value = try {
            1
        } catch (e: Exception) {
            2
        }
    }
}
