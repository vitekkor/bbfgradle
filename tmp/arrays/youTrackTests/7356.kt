// Original bug: KT-27840

fun main(args: Array<String>) {
    val block = Block(false, null)
    println(block.bool)
    println(block.name != null)
    println(block.bool ?: block.name != null)
}

class Block(val bool: Boolean, val name: String?)
