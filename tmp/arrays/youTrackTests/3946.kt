// Original bug: KT-37399

fun main() {
    val propertyWithLongName = true
    require(propertyWithLongName == EnumWithLongName.ConstantWithOverlyLongName.value) { "Some useful message." }
}

enum class EnumWithLongName {
    ConstantWithOverlyLongName {

    };

    val value: Boolean = true
}
