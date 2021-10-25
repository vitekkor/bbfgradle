// Original bug: KT-15794

fun foo() {
    java.util.ArrayList<String>().stream()
}
class A : java.util.ArrayList<String>() {
    override fun stream(): java.util.stream.Stream<String> = super.stream()
}
