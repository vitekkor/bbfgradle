// Original bug: KT-28949

fun main() {
    val devStatus: UInt? = 0x0u
    
    when(devStatus) {
        (0x0u) -> {
            println("0")
        }
        (0x10u) -> {
            println("1")
        }
        (0x20u) -> {
            println("2")
        }
        else -> {
            println("else")
        }
    }
}
