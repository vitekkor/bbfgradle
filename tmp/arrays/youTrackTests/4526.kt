// Original bug: KT-36080

val x: (String) -> Unit = run {
    lambda@ { foo -> // NI: Cannot infer a type...; OI: String (everything is OK without label)
        println(foo)
    }
}
