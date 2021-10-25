// Original bug: KT-11265

data class MyDataClass<T>(val content: T)

fun <T, U> MyDataClass<T>.myExtension(f: (T) -> U): MyDataClass<U> {
   val content = f(this.content)
   return MyDataClass(content)
}

@JvmName("myExtensionUnit")
fun <T> MyDataClass<T>.myExtension(f: (T) -> Unit): MyDataClass<T> {
   f(this.content)
   return this
}

fun doSomethingInt(nr: Int): String {
    return nr.toString()
}

fun doSomethingString(str: String): String {
    return "Hello $str"
}

fun main() {
  val data = MyDataClass<Int>(1)
  println(data.myExtension(::doSomethingInt).myExtension(::doSomethingString))
}
