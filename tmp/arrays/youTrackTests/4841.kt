// Original bug: KT-30750

enum class SimpleEnum {
    A(1),
    B(2),
    C;

    val myValue:Int

    constructor(value:Int) {
        myValue = value
    }

    constructor():this(0)

}


fun main() {
    for(e in SimpleEnum.values()) {
        println("Enum constant ${e.name}") // "Enum constant undefined"
    }
}
