// Original bug: KT-43563

class Example {
    fun foo() {
        // comment 1
        var a = 1
        var b = 2

        // comment 2
        a++
        b++
        
        val str1 = "str1" 
        val str2 = "str2"

        // result comment
        println("%str1, %str2")
    }
}
