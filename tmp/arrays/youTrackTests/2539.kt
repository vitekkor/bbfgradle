// Original bug: KT-17783

interface ContentParent {
    fun fill() = "filler"
    fun accept(p: String) {}
    var faceProp: String
        get() = fill()
        set(p) = accept(p)
} 