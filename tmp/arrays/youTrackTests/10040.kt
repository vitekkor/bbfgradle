// Original bug: KT-9897

class MyClass(var z: String)

object Test {
    val z = MyClass("1")

    fun change(): Unit {
        z.someProperty -= 1
    }
    var MyClass.someProperty: Int
        get() {
            return this.z.length
        }
        set(left) {
            this.z += left
        }
}

fun main(args: Array<String>) {
    Test.change()
}
