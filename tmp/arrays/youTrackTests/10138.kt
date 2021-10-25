// Original bug: KT-2421

fun main(args: Array<String>) {
    System.out.print(format("%s", "expected string"))
}

fun format(base: String, vararg arg: String): String = String.format(base, arg)!!
