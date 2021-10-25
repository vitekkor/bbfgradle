// Original bug: KT-12908

private fun takeTask(): String {
    val task: String

    do {
        if (System.currentTimeMillis() % 1 == 0L) {
            task = ""
            break
        }
    } while (true)

    return task
}


fun main(args: Array<String>) {
    takeTask()
}
