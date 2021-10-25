// Original bug: KT-7526

fun main(args: Array<String>) {

    var n: Number = 42

    val i1: Int = n.toInt()  // Works ok
    print (i1)
    
    if (n is Int) {  
        val i2: Int = n.toInt()  // Incorrect compile error: 'Smart cast is impossible, because n could have changed'
                                 // Yes, n is a var, but it is not nullable and of type Number, 
                                 // so the toInt() method of Number should always be available, 
                                 // even if we were to assign some other number object to it.
        print(i2)
    }
}
