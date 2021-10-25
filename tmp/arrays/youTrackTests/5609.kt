// Original bug: KT-31874

interface SiteFun<R, T : Function<R>> {
    val fn: T
}

class TitleSiteFun : SiteFun<String, Function1<String, String>> {
    override val fn =  { title: String -> "$title - Site" }
}
