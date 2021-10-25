// Original bug: KT-23117

import kotlin.properties.Delegates.notNull

object BugsBunny_Fails {
    @JvmStatic fun main(args: Array<String>) {
        var bunny by notNull<String>()

        val obj = object {
            val getBunny = {bunny}
        }

        bunny = "Hi there"
        println(obj.getBunny())
    }
}

object BugsBunny_Works {
    var bunny by notNull<String>()

    @JvmStatic fun main(args: Array<String>) {
        val obj = object {
            val getBunny = {bunny}
        }

        bunny = "Hi there"
        println(obj.getBunny())
    }
}
