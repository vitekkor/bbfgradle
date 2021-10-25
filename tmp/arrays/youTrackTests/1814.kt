// Original bug: KT-16582

fun printlnAndReturn(toPrint: String): String {
    println(toPrint)
    
    return toPrint;
}

class Person constructor(firstName: String) {
    val firstName = printlnAndReturn("Field value")
    
    val lastName = printlnAndReturn("LastOf " + firstName)
        
    fun printFirstName() {
        println(firstName)
    }
}

fun main(args: Array<String>) {  
    var person = Person(printlnAndReturn("value_from_main"));
    person.printFirstName()
}
