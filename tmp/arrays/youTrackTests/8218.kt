// Original bug: KT-21218

// billf@Zathras:~/tmp$ cat bar.kt

class bar (
    val x : String = "glorp"
) {
    val y : String by lazy { x + "y" }
    fun foo() {
        println("hi, x is $x, y is $y")
    }
}

fun main(args: Array<String>) {
    bar().foo()
}

// billf@Zathras:~/tmp$ kotlinc bar.kt ; kotlin BarKt
// hi, x is glorp, y is glorpy
