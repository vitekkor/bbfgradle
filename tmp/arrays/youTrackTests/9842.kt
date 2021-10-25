// Original bug: KT-11081

open class TypeRef<T> {
    val type = target()

    private fun target(): String {
        val thisClass = this.javaClass
        val superClass = thisClass.genericSuperclass

        return superClass.toString()
    }
}

fun main(args: Array<String>) {
    specifyOptionalArgument()
    useDefault()
}

private fun specifyOptionalArgument() {
    printTypeWithMessage<List<Int>>("Hi")
}

private fun useDefault() {
    printTypeWithMessage<List<Int>>()
}

private inline fun <reified T> printTypeWithMessage(message: String = "Hello") {
    val type = object : TypeRef<T>() {}
    val target = type.type

    println(message + " " + target)
}
