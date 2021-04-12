// Original bug: KT-8745

interface A
interface B

class MyClass : A, B

fun <T> T.ext() where T : A, T : B = 1
fun <T> ext2(me: T) where T : A, T : B = 1

fun main(args: Array<String>) {
    ext2(MyClass()) // works
    MyClass().ext() // doesn't, but should!
}
