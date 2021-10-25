// Original bug: KT-11762

fun String.transformToUnixPath(): String {
    var windowsPath = this
    windowsPath = windowsPath.replace("\\", "/")
    windowsPath = windowsPath.replace(":", "")
    windowsPath = windowsPath.toLowerCase()
    val indexList = windowsPath.getIndexListOfChar(" ")
    for (index: Int in indexList) {
        windowsPath = windowsPath.setCharAt(index, "\\ ")
    }

    return "/" + windowsPath
}

private fun String.getIndexListOfChar(guess: String): ArrayList<Int> {
    var indexList: ArrayList<Int> = ArrayList()

    var index = this.indexOf(guess);
    while (index >= 0) {
        indexList.add(index)
        index = this.indexOf(guess, index + 1);
    }

    return indexList
}

private fun String.setCharAt(index: Int, stringToAdd: String) : String {
    return this.substring(0, index) + stringToAdd + this.substring(index + 1);
}
