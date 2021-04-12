// Original bug: KT-12450

// Call of My() throws NPE
class My {
    init {
        {
            use(build.length)
        }()
    }

    fun use(arg: Int) = arg

    val build = "1234"
}
