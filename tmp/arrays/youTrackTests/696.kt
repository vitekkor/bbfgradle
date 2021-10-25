// Original bug: KT-43797

class A {
    fun test() {
        listOf(3).apply {
            if (size > 3) {
                // Put cursor at the end of this line and press ENTER
                println(4)
            }   
        }
    }
}
