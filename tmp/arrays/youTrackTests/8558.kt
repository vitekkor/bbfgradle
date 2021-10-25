// Original bug: KT-12511

fun box(): String {
    val capturedInConstructor = "cc;"
    val capturedInBody = "cb;"

    var s: Any? = null
    for (t in arrayOf(arrayOf("@", "#"), arrayOf("$", "%"))) {
        val a = t[0]
        val b = t[1]
        class C(var x: String) {
            var y = ""

            inner class D() {
                fun copyOuter(): C {
                    val result = C(x + "xd;")
                    result.y += "|" + capturedInBody + b
                    return result
                }
            }

            init {
                y += x + capturedInConstructor + a
            }

            fun aaa() = a
        }

        if (s == null) {
            s = C("xc;")
        }

        var cc = s as C

        val c = cc.D().copyOuter()
        val s1 = "xc;xd;cc;" + a + "|cb;" + b
        if (c.y != s1) return "fail3a$a: ${c.y} != $s1"
        if (c.x != "xc;xd;") return "fail3b$a: ${c.x}"

        s = cc
    }

    return "OK"
}
