// Original bug: KT-43520

import javax.script.ScriptEngineManager

fun main() {
    with(ScriptEngineManager().getEngineByExtension("kts")) {
        eval("val x = 3")
        val res2 = eval("x + 2")
        println(res2)
    }
}
