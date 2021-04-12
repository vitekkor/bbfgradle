// Original bug: KT-243

fun foo() {
    val i = java.lang.Integer.MAX_VALUE
    val j = java.lang.Integer.valueOf(15)
    val s = java.lang.String.valueOf(1)
    val t = java.lang.String.copyValueOf(java.lang.String("s").toCharArray())
    val l = java.util.Collections.emptyList<Int>()
}
