// Original bug: KT-33960

fun main() {
 val lk = java.lang.invoke.MethodHandles.lookup()
 val obj = "test"
 val mt = java.lang.invoke.MethodType.methodType(Int::class.java)
 val mh = lk.bind(obj, "length", mt);
 val len = mh.invoke()
}
