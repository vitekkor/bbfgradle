// Original bug: KT-30753

var x = 1
val y = ""
val w: Any by lazy {
    if (x == 2)
        x // warning: Conditional branch result of type Int is implicitly cast to Any
    else
        y // warning: Conditional branch result of type String is implicitly cast to Any
}
