// Original bug: KT-31299

inline class MapWrapper(val map: MutableMap<String, MapWrapper?>)

class A99(val mapWrapper: MapWrapper = MapWrapper(mutableMapOf())) {
    var p: A99? = null
}

fun main() {
    val o2 = A99()
    o2.mapWrapper.map["x"] = A99().mapWrapper
    o2.mapWrapper.map["x"] is MapWrapper // no CCE
    o2.mapWrapper.map["x"] = A99()?.mapWrapper
    o2.mapWrapper.map["x"] is MapWrapper // CCE
}
