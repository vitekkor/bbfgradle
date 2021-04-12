// Original bug: KT-13116

open class MapModel2 : HashMap<String, String> () {
    companion object{}
}
class abc : MapModel2() {
}
fun main(arg: Array<String>) {
    var a = abc();
    a["aName"] = "OK";
    println(a["Name"]);
}

