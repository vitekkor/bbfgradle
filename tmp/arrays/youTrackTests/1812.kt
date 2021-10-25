// Original bug: KT-35508

val doesNotWork = """=== EREIGNISLISTE ======
""" + "\u001b" + """Kn
BEGINN       28.06 13:25
EREIGNISSE            62
50 5          28.06 1325
3402          28.06 1325
3412          28.06 1325
63 3          28.06 1325
63 0          28.06 1325
EE06          28.06 1325
EE06          28.06 1322
EE07          28.06 1322
63 3          28.06 1322
EE06          28.06 1322
EE07          28.06 1322
63 3          28.06 1322
63 3          28.06 1322
63 3          28.06 1323
63 3          28.06 1500
50 4          28.06 1500
50 5          30.06 1226
3402          30.06 1226
3412          30.06 1226
50 4          30.06 1227
50 5          30.06 1228
3402          30.06 1228"""

val works = """=== EREIGNISLISTE ======
""" + "\u001b" + """Kn
BEGINN       28.06 13:25
EREIGNISSE            62
50 5          28.06 1325
3402          28.06 1325
3412          28.06 1325
63 3          28.06 1325
63 0          28.06 1325
EE06          28.06 1325
EE06          28.06 1322
EE07          28.06 1322
63 3          28.06 1322
EE06          28.06 1322
EE07          28.06 1322
63 3          28.06 1322
63 3          28.06 1322
63 3          28.06 1323
63 3          28.06 1500
50 4          28.06 1500
50 5          30.06 1226
3402          30.06 1226
3412          30.06 1226
50 4          30.06 1227
50 5          30.06 1228"""

val REGEX = Regex("(\\x1b\\w[\\s\\S]{1,2})([\\s\\S]+?(?=\\x1b\\w[\\s\\S]{1,2}|\$))")


fun regexTest(content: String): List<String> {
    return REGEX.findAll(content).map {
        it.groupValues[1]
    }.toList()
}

fun f35508(){
    println(regexTest(works))
    println("First string succeeded")
    println(regexTest(doesNotWork)) //fails here
    println("Second string succeeded")
}
