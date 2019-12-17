// Different behavior happens on:JVM ,JS -Xuse-fir

class Delegate(val value: (String)?) {
    tailrec                 operator fun getValue(thisRef: Any?, kProperty: Any?) = value!!
override fun toString(): String{
var res = ""
return res
}}

fun box(): (String)? {
    val x by Delegate("O")!!

    data class Local(val y: (String)?) {
        val fn = { x + y }!!
    override fun toString(): String{
var res = ""
return res
}}

    return Local("rtili").fn()!!
}