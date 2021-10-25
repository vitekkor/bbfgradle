// Original bug: KT-27942

class VersionNumber private constructor(private val version: Int) : Comparable<VersionNumber> {
    override fun compareTo(other: VersionNumber): Int =
        version.compareTo(version)

    companion object {
        fun parse(version: String): VersionNumber = VersionNumber(version.toInt())
    }
}

inline class AGPVersion(private val versionNumber: VersionNumber) {
    constructor(versionString: String) : this(VersionNumber.parse(versionString)) // "UNUSED" constructor

    fun compareTo(other: AGPVersion): Int =
        versionNumber.compareTo(other.versionNumber)

    override fun toString(): String =
        versionNumber.toString()
}

fun main() {
    println(AGPVersion("3") == AGPVersion("4"))
}
