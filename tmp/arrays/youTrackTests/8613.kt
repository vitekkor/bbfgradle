// Original bug: KT-20179

annotation class Ann(vararg val v: String)

@Ann(*arrayOf("a"), *arrayOf("b")) // in bytecode: v={{"a"}, {"b"}}
fun test1() {}

@Ann(*arrayOf(*arrayOf("a"))) // in bytecode: v={{"a"}}
fun test2() {}

@Ann(*["a"], *["b"]) // in bytecode: v={{"a"}, {"b"}}
fun test3() {}
