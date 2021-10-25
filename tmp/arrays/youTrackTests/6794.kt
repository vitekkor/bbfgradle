// Original bug: KT-4687

interface HostTrait

enum class Enum1 : HostTrait{ item }
enum class Enum2 : HostTrait{ item }

fun test() {
    try {
        // returns Any
        Enum1.item
    } catch(e: Exception) {
        Enum2.item
    }

    if (true) {
        // returns Any
        Enum1.item
    } else {
        Enum2.item
    }
}
