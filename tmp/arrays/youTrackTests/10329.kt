// Original bug: KT-6897

fun TABLE.tableContent(): () -> TABLE {
    tbody {
        return@tableContent {this}
    }

    return {this}
}

inline fun TABLE.tbody(contents:  () -> Unit) = contents()

class TABLE {

}

fun main(args: Array<String>) {
    TABLE().tableContent()
}
