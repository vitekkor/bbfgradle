// Original bug: KT-19106

class BClass{
    fun f1(){}
}
fun f2(b: BClass) {}
fun f3(param: BClass){ // call `Data Flow from here` for `param`
    val a1 = param
    val b1 = param.f1()
    val c1 = f2(param)
}