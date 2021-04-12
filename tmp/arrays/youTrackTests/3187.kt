// Original bug: KT-19036

data class AClass(val s: String){
    fun memberFun() = s
}

fun AClass.extFun() = this.s

fun main(args: Array<String>) {
    val a = AClass("hello") // call Analyze Data Flow from here for `a`
    a.memberFun() // a is found, because `memberFun` is a member fun - OK
    a.extFun() // a not found, because `extFun` is an extension fun
}