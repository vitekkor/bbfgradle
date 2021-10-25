// Original bug: KT-41890

fun main(){
    val propertyPattern = "#\\?\\s*(?<key>[\\w.]*)\\s*:\\s*(?<value>[^;]*);?".toRegex()
    val result = propertyPattern.matches("#? aaa.ddd : 22")
    println(result)
}
