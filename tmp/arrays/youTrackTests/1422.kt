// Original bug: KT-37883

fun doRestructureKtDTs(reader: java.io.Reader): java.io.Reader {
    val content = reader.readLines().toMutableList()
    if (content.size > 0) {
        // drop declare namespace wrapper
        if (content[0].startsWith("declare namespace")) {
            content[0] = ""
            content[content.lastIndex] = ""
        }
        // export all namespaces
        val regex = Regex("""^\s+namespace""")
        for ((i, line) in content.withIndex()) {
            if (regex.find(line) != null) {
                content[i] = line.replaceFirst("namespace", "export namespace")
            }
        }
    }
    return content.joinToString(separator = "\n").reader()
}
