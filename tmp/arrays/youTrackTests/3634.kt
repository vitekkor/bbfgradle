// Original bug: KT-33167

fun question(subject: String, names: Array<String> = emptyArray()): String {
    return buildString { // breakpoint here
        append("$subject?") // actual stop
        for (name in names)
            append(" $name?")
    }
}

fun main(args: Array<String>) {
    println(question("Subject", args))    
}
