// Original bug: KT-42811

    fun main(args: Array<String>) {
       println("Hello, Kotlin/Native!")

        println("Simple print: ÐÐµÑÑ!")

        args.forEach {
            println(it)
        }
    }
    