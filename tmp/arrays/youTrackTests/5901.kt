// Original bug: KT-21131

    private class C {
       var m: String = ""
    }

    @Deprecated("", ReplaceWith("m"))
    private var C.old: String
        get() = m
        set(value) {
            m = value
        }
    
    private fun use(c: C) {
        c.old   // try to replace it
    }
