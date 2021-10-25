// Original bug: KT-35644

fun foo(number: Int?) {
    val a = if (number != null) number else throw NullPointerException("Expression 'number' must not be null") 
    println(1 + a)
}

fun main(){
    foo(null)// result is java.lang.NullPointerException: Expression 'number' must not be null
}
