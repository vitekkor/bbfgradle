// Original bug: KT-28903

package examples

object Test
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        println(UInt::class.java)         // int
        println(kotlin.UInt::class.java)  // int
        println(UInt::class)              // class kotlin.Int
        println(kotlin.UInt::class)       // class kotlin.Int

        val uint: UInt = 0u
        println(uint::class.java)         // int

        val nuint: UInt? = 0u
        println(nuint!!::class.java)      // class kotlin.UInt - the only working solution!
    }
}
