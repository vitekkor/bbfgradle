// Original bug: KT-39884

fun foo(
    param: String,
    optionalParam: Int = 6
) {
    bar(
        param,
        optionalParam
    )
}

fun bar(
    param: String,
    optionalParam: Int = 6
) {

}
