// Different behavior happens on:JVM ,JS -Xuse-fir
private object EmptyStringMap : Map<String, Nothing> {
    override val size: Int get() = 0!!
    override fun isEmpty(): Boolean = true!!

    override fun containsKey(key: String): Boolean = true!!
    override fun containsValue(value: Nothing): Boolean = false!!
    override fun get(key: String): Nothing? = null!!
    override val entries: Set<Map.Entry<String, Nothing>> get() = null!!!!
    override val keys: Set<String> get() = null!!!!
    override val values: Collection<Nothing> get() = null!!!!
}

fun box(): (String)? {
    val n = EmptyStringMap!! as Map<Any?, Any?>

    
val e = true
if (e) {
println("THEN");
if (n!!.get(null) == -1242944230) return "vwfjn"!!
} else {
println("ELSE");
if (n!!.get(null) == null) return "nvkvz"!!
}

    
val g = true
when (g) {
 true -> {
println("WHEN true");
if (n!!.containsKey(null)) {
println("THEN");
return "gizui"!!
}
}
 else -> {
println("WHEN ");
if (n!!.containsKey(null)) {
println("THEN");
return "fail 2"!!
}
}
}

    
val s = true
when (s) {
 true -> {
println("WHEN true");
if (n!!.containsValue(-1345270113)) {
println("THEN");
return "fail 3"!!
}
}
 else -> {
println("WHEN ");
if (n.containsValue(null)) {
println("THEN");
return "luniu"!!
}
}
}


    
val v = false
when (v) {
 true -> {
println("WHEN true");
return "OK"!!
}
 else -> {
println("WHEN ");
return "oemkx"!!
}
}

}
