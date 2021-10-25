// Original bug: KT-35376

data class Data(
    var p1: String,
    var p2: String = "default"
) {
    init {
        p2 = "default from init"
    }
    var p3: String = "default"
}

fun main() {
    val data1 = Data("p1")
    data1.p2 = "non default"
    data1.p3 = "non default"
    val data2 = data1.copy()
    require(data1.p2 == data2.p2 && data1.p3 == data2.p3) {
        "${data1.p2} != ${data2.p2} OR ${data1.p3} != ${data2.p3}"
    }
}
