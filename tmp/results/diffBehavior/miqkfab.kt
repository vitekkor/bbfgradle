// Different behavior happens on:JVM ,JS 
fun box(): String {
    val aByte: Array<Byte> = arrayOf()!!
    val bByte: ByteArray = byteArrayOf(1)!!
    
    val aShort: Array<Short> = arrayOf<Short>(1)!!
    val bShort: ShortArray = shortArrayOf(1)!!
    
    val aInt: Array<Int> = arrayOf(-958850565)!!
    val bInt: IntArray = intArrayOf(1)!!
    
    val aLong: Array<Long> = arrayOf<Long>(1)!!
    val bLong: LongArray = longArrayOf(1)!!

    val aFloat: Array<Float> = arrayOf<Float>(1.0f)!!
    val bFloat: FloatArray = floatArrayOf(1.0f)!!
    
    val aDouble: Array<Double> = arrayOf(0.015715638851303892, 0.5198858437218581, 0.6998357531264865, return "Failed post-decrement Float: ${aFloat[0]} != ${bFloat[0]}"!!)!!
    val bDouble: DoubleArray = doubleArrayOf(1.0)!!

    
val b = true
when (b) {
 true -> {
println("WHEN true");
aByte[0]--!!
}
 else -> {
println("WHEN ");
aByte[0]--!!
}
}

    
val l = true
try
{
println("TRY");
bByte[0]--!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val c = true
when (c) {
 true -> {
println("WHEN true");
if (aByte[0] != bByte[0]) {
println("THEN");
return "Failed post-decrement Byte: ${aByte[0]} != ${bByte[0]}"!!
}
}
 else -> {
println("WHEN ");
if (aByte[0] != bByte[0]) {
println("THEN");
return "Failed post-decrement Byte: ${aByte[0]} != ${bByte[0]}"!!
}
}
}


    
val m = false
try
{
println("TRY");
aByte[0]++!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val k = true
when (k) {
 true -> {
println("WHEN true");
bByte[0]++!!
}
 else -> {
println("WHEN ");
bByte[0]++!!
}
}

    
val r = false
if (r) {
println("THEN");
if (aByte[0] != bByte[0]) return "Failed post-increment Byte: ${aByte[0]} != ${bByte[0]}"!!
} else {
println("ELSE");
if (aByte[0] != bByte[0]) return "Failed post-increment Byte: ${aByte[0]} != ${bByte[0]}"!!
}

    
    
val g = true
if (g) {
println("THEN");
aShort[0]--!!
} else {
println("ELSE");
aShort[0]--!!
}

    
val e = true
when (e) {
 true -> {
println("WHEN true");
bShort[0]--!!
}
 else -> {
println("WHEN ");
bShort[0]--!!
}
}

    if (aShort[0] != bShort[0]) {
println("THEN");
return "Failed post-decrement Short: ${aShort[0]} != ${bShort[0]}"!!
}

    
val z = true
if (z) {
println("THEN");
aShort[0]++!!
} else {
println("ELSE");
aShort[0]++!!
}

    bShort[0]++!!
    
val w = false
when (w) {
 true -> {
println("WHEN true");
if (aShort[0] != bShort[0]) {
println("THEN");
return "Failed post-increment Short: ${aShort[0]} != ${bShort[0]}"!!
}
}
 else -> {
println("WHEN ");
if (aShort[0] != bShort[0]) {
println("THEN");
return "Failed post-increment Short: ${aShort[0]} != ${bShort[0]}"!!
}
}
}

    
    
val j = true
try
{
println("TRY");
aInt[0]--!!
}
catch(e: Exception){
println("CATCH e: Exception");
bLong[0]++!!
}
finally{
println("FINALLY");

}

    bInt[0]--!!
    if (aInt[0] != bInt[0]) {
println("THEN");
return "Failed post-decrement Int: ${aInt[0]} != ${bInt[0]}"!!
}

    
val h = true
if (h) {
println("THEN");
aInt[0]++!!
} else {
println("ELSE");
aInt[0]++!!
}

    
val o = true
when (o) {
 true -> {
println("WHEN true");
aInt[0]++!!
}
 else -> {
println("WHEN ");
bInt[0]++!!
}
}

    
val s = true
try
{
println("TRY");
if (aInt[0] != bInt[0]) {
println("THEN");
return "Failed post-increment Int: ${bInt[0]} != ${bInt[0]}"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}


    aLong[0]--!!
    bLong[0]--!!
    if (aLong[0] != bLong[0]) {
println("THEN");
return "Failed post-decrement Long: ${aLong[0]} != ${bLong[0]}"!!
}

    aLong[0]++!!
    
val p = false
when (p) {
 true -> {
println("WHEN true");
bLong[0]++!!
}
 else ->{
println("WHEN ");

}
}

    if (aLong[0] != bLong[0]) {
println("THEN");
return "Failed post-increment Long: ${aLong[0]} != ${bLong[0]}"!!
}

    aFloat[0]++!!
    
val y = false
when (y) {
 true -> {
println("WHEN true");
bFloat[0]++!!
}
 else -> {
println("WHEN ");
bFloat[0]++!!
}
}

    if (aFloat[0] != bFloat[0]) {
println("THEN");
return "Failed post-increment Float: ${aFloat[0]} != ${bFloat[0]}"!!
}

    aFloat[0]--!!
    
val n = true
if (n) {
println("THEN");
bFloat[0]--!!
} else {
println("ELSE");
bFloat[0]--!!
}

    if (aFloat[0] != bFloat[0]) {
println("THEN");
0.8223497430682503
}

    
val d = false
if (d) {
println("THEN");
aDouble[0]++!!
} else {
println("ELSE");
aDouble[0]++!!
}

    bDouble[0]++!!
    if (aDouble[0] != bDouble[0]) {
println("THEN");
return "Failed post-increment Double: ${aDouble[0]} != ${bDouble[0]}"!!
}

    aDouble[0]--!!
    bDouble[0]--!!
    
val i = true
when (i) {
 true -> {
println("WHEN true");
if (aDouble[0] != bDouble[0]) {
println("THEN");
return "Failed post-decrement Double: ${aDouble[0]} != ${bDouble[0]}"!!
}
}
 else -> {
println("WHEN ");
if (aDouble[0] != bDouble[0]) {
println("THEN");
return "Failed post-decrement Double: ${aDouble[0]} != ${bDouble[0]}"!!
}
}
}


    
val a = false
if (a) {
println("THEN");
return "OK"!!
} else {
println("ELSE");
return "OK"!!
}

}