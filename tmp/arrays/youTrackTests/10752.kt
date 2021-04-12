// Original bug: KT-2203

fun escapeChar(c: Char): String? = when (c) {
    '\\' -> "\\\\"
    '\n' -> "\\n"
    '"'  -> "\\\""
    else -> "" + c
}

fun String.escape(i: Int = 0, result: String = ""): String =
if (i == length) result
else escape(i + 1, result + escapeChar(get(i)))

fun box(): String {
    val s = "  println(\"fun escapeChar(c : Char) : String? = when (c) {\");\n  println(\"  '\\\\\\\\' => \\\"\\\\\\\\\\\\\\\\\\\"\");\n  println(\"  '\\\\n' => \\\"\\\\\\\\n\\\"\");\n  println(\"  '\\\"'  => \\\"\\\\\\\\\\\\\\\"\\\"\");\n  println(\"  else => String.valueOf(c)\");\n  println(\"}\");\n  println();\n  println(\"fun String.escape(i : Int = 0, result : String = \\\"\\\") : String =\");\n  println(\"  if (i == length) result\");\n  println(\"  else escape(i + 1, result + escapeChar(this.get(i)))\");\n  println();\n  println(\"fun main(args : Array<String>) {\");\n  println(\"  val s = \\\"\" + s.escape() + \"\\\";\");\n  println(s);\n}\n";
    println("fun escapeChar(c : Char) : String? = when (c) {");
    println("  '\\\\' => \"\\\\\\\\\"");
    println("  '\\n' => \"\\\\n\"");
    println("  '\"'  => \"\\\\\\\"\"");
    println("  else => String.valueOf(c)");
    println("}");
    println();
    println("fun String.escape(i : Int = 0, result : String = \"\") : String =");
    println("  if (i == length) result");
    println("  else escape(i + 1, result + escapeChar(this.get(i)))");
    println();
    println("fun main(args : Array<String>) {");
    println("  val s = \"" + s.escape() + "\";");
    println(s);
    return "OK"
}
