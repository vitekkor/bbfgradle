// Original bug: KT-41777

interface CanDoStuff {
    companion object

    suspend fun doStuff() // <-- "Class name'suspend' should start with an uppercase letter"
    suspend fun doOtherStuff()
}
