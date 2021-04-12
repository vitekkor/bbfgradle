// Original bug: KT-17719

inline fun divCheck(a: Int, b: Int) : Int {
    if (b==0)
        throw Error("Div by zero")
    else
        return a / b

}

inline fun remCheck(a: Int, b: Int) : Int {
    if (b==0)
        throw Error("Div by zero")
    else
        return a % b

}
