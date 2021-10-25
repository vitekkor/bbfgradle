// Original bug: KT-28283

class Legacy {
    val nullString: String = getNull()
    val uninitialized: String = "unused"
    
    fun getNull(): String {
        return uninitialized
    }
}

class B {
    val x
    	get() = Legacy().nullString?.plus("0")
}

fun main(args: Array<String>) {
    print(B().x?.length)  // java.lang.NullPointerException
}
