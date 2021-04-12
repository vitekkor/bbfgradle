// Original bug: KT-42125

class X(
    x: String,
) {

    fun declaration(
        x: String,
    ) = Unit

    fun usage() {
        declaration(
            x = "string"
        )
    }
}
