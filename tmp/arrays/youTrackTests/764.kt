// Original bug: KT-44300


class KotlinApi {
    fun samAcceptingMethod(block: Runnable) {
        println("> KotlinApi")
        block.run()
    }
}

class KotlinExtension {
    fun samAcceptingMethod(block: Runnable) {
        println("> KotlinExtension")
        block.run()
    }
}

fun KotlinApi.script() {
    samAcceptingMethod {
        KotlinExtension().apply {
            samAcceptingMethod {
            }
        }
    }
}

fun main() {
    KotlinApi().script()
}
