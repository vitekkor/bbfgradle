// Original bug: KT-22714

val array:Array<String> = arrayOf("1", "2", "3")

fun <T> Array<T>.getLength(): Int {
return this.size
}

fun box(): String {
if (array.getLength() != 3)
return "FAILURE"
return "OK"
}
