// Original bug: KT-11116

fun test(ss: List<String?>) {
    val shortStrings = hashSetOf<String>()
    val longStrings = hashSetOf<String>()
    for (s in ss) {
        s?.let {
            if (s.length < 4) {
                shortStrings.add(s) // Boolean
            }
            else {
                longStrings.add(s) // Boolean
            }            
        }
    }
    // ...
}
