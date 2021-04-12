// Original bug: KT-16745

enum class DemoEnum(val value : String) {
    One("one"),
    Two("two"),
    Three("three");
    
    init {
        println("DemoEnum.<init>")
    }
    
    companion object {
        init {
            println("DemoEnum.companion")
            val value = DemoEnum.values()[0].value
        }
    }
}

fun main(args: Array<String>) {
    val one = DemoEnum.One
}
