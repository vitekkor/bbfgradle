// Original bug: KT-32028

enum class INum {
    INumMemberA;
    var enumProp: String = "some value"
}

fun main() {
    println(INum.INumMemberA.enumProp)
    INum.INumMemberA.enumProp = "another value"
    println(INum.INumMemberA.enumProp)
}
