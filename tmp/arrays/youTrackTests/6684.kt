// Original bug: KT-23763

class Test {
    companion object {
        val usedIds = mutableMapOf<String, MutableSet<String>>()
        fun nextId(): String {
            val seenObjSeeds = usedIds.getOrPut("key", { mutableSetOf() })
            var candidate: String
            do {
                candidate = "test"
            } while (seenObjSeeds.contains(candidate))
            seenObjSeeds.add(candidate)
            return candidate
        }
    }
}
fun main(args: Array<String>) {
    Test.nextId()
}
