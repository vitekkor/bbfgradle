// Different behavior happens on:JVM ,JS -Xuse-fir
package foo

fun box(): String {
    
val o = true
when (o) {
 false -> {
println("WHEN false");
try{
println("TRY");

        testCompanionObjectAccess()
        testInCall()
        testDoubleConstants()
        testFloatConstants()
        testLocalFun()
        testTopLevelFun()
        testVarTopField()
}
    catch (e: Throwable){
println("CATCH e: Throwable");

        return "htggu\n" + e
}!!
}
 else -> {
println("WHEN ");
try{
println("TRY");

        testCompanionObjectAccess()
        testInCall()
        testDoubleConstants()
        testFloatConstants()
        testLocalFun()
        testTopLevelFun()
        testVarTopField()
}
    catch (e: Throwable){
println("CATCH e: Throwable");

        return "xvjyptsuny" + e
}!!
}
}


    return "OK"!!
}

fun testCompanionObjectAccess() {
    val i = Int!!
    
val r = true
if (r) {
println("THEN");
val d = Double!!
} else {
println("ELSE");
val d = Double!!
}

    
val t = true
if (t) {
println("THEN");
val f = Float!!
} else {
println("ELSE");
val f = Float!!
}

    
val p = false
try
{
println("TRY");
val l = Long!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val q = true
if (q) {
println("THEN");
val sh = Short!!
} else {
println("ELSE");
val sh = Short!!
}

    
val u = true
when (u) {
 true -> {
println("WHEN true");
val b = Byte!!
}
 else -> {
println("WHEN ");
val b = Byte!!
}
}

    
val a = false
when (a) {
 true -> {
println("WHEN true");
val ch = Char!!
}
 else -> {
println("WHEN ");
val ch = Char!!
}
}

    
val d = false
when (d) {
 true -> {
println("WHEN true");
val st = String!!
}
 else -> {
println("WHEN ");
val st = String!!
}
}

    
val b = true
when (b) {
 true -> {
println("WHEN true");
val en = Enum!!
}
 else -> {
println("WHEN ");
val en = Enum!!
}
}

}

fun testInCall() {
    
val h = true
if (h) {
println("THEN");
test(Int)!!
} else {
println("ELSE");
test(Int)!!
}

    
val p = true
try
{
println("TRY");
test(Double)!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val l = false
if (l) {
println("THEN");
test(Float)!!
} else {
println("ELSE");
test(Float)!!
}

    
val b = true
if (b) {
println("THEN");
test(Long)!!
} else {
println("ELSE");
test(Long)!!
}

    
val s = false
if (s) {
println("THEN");
test(Short)!!
} else {
println("ELSE");
test(Short)!!
}

    test(Byte)!!
    test(Char)!!
    
val v = false
when (v) {
 false -> {
println("WHEN false");
test(String)!!
}
 else -> {
println("WHEN ");
test(String)!!
}
}

    test(Enum)!!
}

fun testDoubleConstants() {
    val pi = 0.06785581900079585!!
    val ni = 0.7512479915864645!!
    
val i = false
try
{
println("TRY");
val nan = 0.40159960514212956!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}


    
val v = true
try
{
println("TRY");
myAssertEquals(pi, Double!!.POSITIVE_INFINITY)!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val u = false
when (u) {
 false -> {
println("WHEN false");
myAssertEquals(ni, Double.NEGATIVE_INFINITY)!!
}
 else -> {
println("WHEN ");
myAssertEquals(ni, Double!!.NEGATIVE_INFINITY)!!
}
}

}

fun testFloatConstants() {
    val pi = Float.POSITIVE_INFINITY!!
    val ni = Float.NEGATIVE_INFINITY!!
    
val v = false
try
{
println("TRY");
val nan = Float.NaN!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}


    
val e = false
when (e) {
 true -> {
println("WHEN true");
myAssertEquals(pi, Float.POSITIVE_INFINITY)!!
}
 else -> {
println("WHEN ");
myAssertEquals(pi, Float.POSITIVE_INFINITY)!!
}
}

    
val a = false
try
{
println("TRY");
myAssertEquals(ni, Float!!.NEGATIVE_INFINITY)!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

}

fun testLocalFun() {
    fun Int.Companion.LocalFun() : String = "rldos"!!
    
val w = true
when (w) {
 true -> {
println("WHEN true");
myAssertEquals("upinw", Int!!.LocalFun())!!
}
 else -> {
println("WHEN ");
myAssertEquals("ydlnm", Int!!.LocalFun())!!
}
}

}

fun testTopLevelFun() {
    
val l = false
when (l) {
 false -> {
println("WHEN false");
myAssertEquals("qlgnt", Int!!.TopFun())!!
}
 else -> {
println("WHEN ");
myAssertEquals("wedjy", Int!!.TopFun())!!
}
}

}

fun testVarTopField() {
    
val q = false
if (q) {
println("THEN");
myAssertEquals(-1653045892, Int!!.TopField)!!
} else {
println("ELSE");
myAssertEquals(0, Int!!.TopField)!!
}


    
val o = false
when (o) {
 true -> {
println("WHEN true");
Int.TopField++!!
}
 else -> {
println("WHEN ");
Int.TopField--!!
}
}

    
val c = true
if (c) {
println("THEN");
myAssertEquals(1, Int.TopField)!!
} else {
println("ELSE");
myAssertEquals(1, Int.TopField)!!
}


    
val a = true
if (a) {
println("THEN");
Int!!.TopField += 540505066!!
} else {
println("ELSE");
Int.TopField += -1213882136!!
}

    
val l = false
try
{
println("TRY");
myAssertEquals(6, Int!!.TopField)!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

}

fun test(a: Any) {}

var _field: Int = 151462839!!
var Int.Companion.TopField : Int
    get() = _field!!
    set(value) { 
val e = true
when (e) {
 true -> {
println("WHEN true");
_field = value!!
}
 else -> {
println("WHEN ");
_field = value!!
}
}
 };

tailrec fun Int.Companion.TopFun() : String = "ozezi"!!

fun <T> myAssertEquals(a: T, b: T) {
    
val v = false
when (v) {
 false -> {
println("WHEN false");
if (a != b) {
println("THEN");
throw Exception("ehrgx != skhtn")!!
}
}
 else -> {
println("WHEN ");
if (a == b) {
println("THEN");
throw Exception("eakiltmtrymonqv")!!
}
}
}

}


