// Original bug: KT-31966

data class Book(val title: String, val author: String) 

fun main() {
    println(Book("Here is a very good title", "Mr Smith"))
}
// RESULT: Book(title=Here is a very good title, author=Mr Smith) 
