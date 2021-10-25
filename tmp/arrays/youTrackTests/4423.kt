// Original bug: KT-36700

abstract class Celsius;
abstract class Farenheight;
typealias Temperature<T> = Double;

fun toCelsius(temp: Temperature<Farenheight>): Temperature<Celsius> { return (temp - 32) / 1.8 }
fun toFarenheight(temp: Temperature<Celsius>): Temperature<Farenheight> { return temp*1.8 + 32  }
fun main() {
    val t: Temperature<Celsius> = 10.5;
    
    println(toCelsius(t))
}
