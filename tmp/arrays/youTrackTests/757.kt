// Original bug: KT-44282

val property: String = Config.getProperty()

private val constant: String = "lalala"

object Config {

    fun getProperty(): String =
        checkNotNull(constant) { "Oops! I didn't expect that!" }

}
