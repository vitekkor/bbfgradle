// Original bug: KT-45700

// parameters[1].isVarArgs == true
fun hasVarargParameter(name: String, vararg params: Any) {}

// parameters[0].isVarArgs == false
fun hasVarargParameter2(vararg params: Any, name: String) {}
