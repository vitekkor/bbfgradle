// Original bug: KT-31203

class Data(val foo: Int) // with cursor on "foo" do "Find Usages"

fun bar() {
    val data1 = Data(1) // usage NOT found
    val data2 = Data(foo = 1) // usage found
    println(data2.foo) // usage found
}
