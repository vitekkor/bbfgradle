// Original bug: KT-27248

data class Test(var v1: String)
data class TestMain(var t1:String, var t2: MutableSet<Test>)

fun main(args: Array<String>) {

    var var1 = TestMain("15", mutableSetOf(Test(v1="3"), Test(v1="2"), Test(v1="8")))
    var var2 = TestMain("15", mutableSetOf(Test(v1="1"), Test(v1="2"), Test(v1="8")))

    var1.t2.forEach { r-> if (r.v1 == "3") r.v1 = "1"}
   
    println(var1.hashCode())
    println(var2.hashCode())
    println(var1)
    println(var2)
    println(var1.equals(var2))
}
