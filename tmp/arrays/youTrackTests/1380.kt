// Original bug: KT-43987

var l = ""

enum class Foo {
    FOO,
    BAR;

    init {
        l += "Foo.$name;"
    }

    companion object {
        init {
            l += "Foo.CO;"
        }

        val boo = 22
    }
}

enum class Foo2 {
    FOO,
    BAR;

    init {
        l += "Foo2.$name;"
    }

    companion object {
        init {
            l += "Foo2.CO;"
        }

        val boo = values()[1]
    }
}

fun box(): String {
    if (Foo.boo != 22) return "fail1"
    if (l != "Foo.FOO;Foo.BAR;Foo.CO;") return "l = $l"

    l = ""
    if (Foo2.boo != Foo2.BAR) return "fail2"
    if (l != "Foo2.FOO;Foo2.BAR;Foo2.CO;") return "l = $l"

    return "OK"
}
