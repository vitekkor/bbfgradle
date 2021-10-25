// Original bug: KT-42435

interface RBuilder {
    fun div(content: RBuilder.() -> Unit)
    fun span(text: String)
}

interface PageModule {
    fun RBuilder.PageBreak()
    fun RBuilder.PageFooter(copyright: String)
    fun RBuilder.PageHeader(title: String)
}

fun RBuilder.WithOptionalPageModule(content: RBuilder.(module: PageModule?) -> Unit) {
    // â¦
}

fun RBuilder.App() {
    WithOptionalPageModule { module ->
        with(module) { // multiple receivers please
            if (this != null) // ugh - how to improve?
                PageHeader(title = "A page.")

            div {
                span("Hello world.")

                if (this@with != null) // ugh - how to improve?
                    PageBreak()

                span("How are you?")
            }

            if (this != null) // ugh - how to improve?
                PageFooter(copyright = "me")
        }
    }
}
