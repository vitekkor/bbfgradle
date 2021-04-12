// Original bug: KT-27810

class C {
    private suspend fun TestPrivate()
//  ^^^^^^^ If this private modifier is removed then everything works as expected.
    {
        println("request handled") // <<< try to set breakpoint here
        println("another line") // <<< and here
    }

    suspend fun Test()
    {
        TestPrivate()
    }
}

suspend fun main(args: Array<String>)
{
    val c = C()
    while (true) {
        c.Test()
    }
}
