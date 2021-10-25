// Original bug: KT-30939

interface A
interface B
class CAB : A, B 
class CA : A
class CB : B

fun <T : A> test(s: String, a: Any) where T : B {
    val t = a as? T
    println(s + " " + (if (t == null) "<false>" else "<true>"))
}

inline fun <reified T : A> test2(s: String, a: Any) where T : B {
    val t = a as? T
    println(s + " " + (if (t == null) "<false>" else "<true>"))
}

fun main(args: Array<String>) {
    test<CAB>("CAB", CAB())
    test<CAB>("CA ", CA())
    test<CAB>("CB ", CB())
    test<CAB>("Any", Any())

    test2<CAB>("CAB", CAB())
    test2<CAB>("CA ", CA())
    test2<CAB>("CB ", CB())
    test2<CAB>("Any", Any())
}
