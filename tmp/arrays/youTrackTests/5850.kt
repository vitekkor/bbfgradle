// Original bug: KT-29239

fun main(args: Array<String>) {

    val context = object : Interface, AnotherInterface {
        override val guy: AnotherInterface get() = this
    }

    with(context) {
        TargetClass("Here we go!")
            .perform()
    }

}

data class TargetClass(val value: String)

interface Interface {

    val guy: AnotherInterface

    fun TargetClass.perform() {
        println("Target class perform")

        DifferentClass(value)
            .perform()
    }

    private fun DifferentClass.perform() = with(guy) {
        perform()
    }

}

data class DifferentClass(val value: String)

interface AnotherInterface {

    fun DifferentClass.perform() {
        println("DifferentClass perform")
    }

}
