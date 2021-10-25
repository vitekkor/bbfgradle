// Original bug: KT-44701

inline class Location @JvmOverloads constructor(val value: String? = null) {

    fun append(inner: String): Location = if (value == null) {
        Location(inner)
    } else {
        Location("$value//$inner")
    }
}
