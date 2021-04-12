// Original bug: KT-43608

enum class Type(val number: Int) {
    SUCCESS(12);

    fun getDepartmentInfo(): String = "Department number: $number"
}

fun foo() {
    Type.SUCCESS.number
}
