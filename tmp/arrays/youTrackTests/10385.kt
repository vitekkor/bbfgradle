// Original bug: KT-6202

fun createPage(pageNum: Int, position: String): String? {
    var pageView: String? = "123"
    if (pageView == null) {
        pageView = ""
    } else {
        pageView.toString()
    }
    print(pageNum)
    return pageView
}
