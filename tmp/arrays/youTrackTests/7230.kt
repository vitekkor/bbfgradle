// Original bug: KT-28108

@kotlin.ExperimentalUnsignedTypes
data class Bar(val primaryID: UInt,
               val secondaryID: UInt)



@kotlin.ExperimentalUnsignedTypes
class Foo() {
    private class Baz(val callback: ((UInt, UInt) -> Unit)?,
                      val primaryID: UInt?,
                      val secondaryID: UInt?)

    private val bazCallbacks: MutableList<Baz> = ArrayList()
    
    fun addBazCallback(callback: (UInt, UInt) -> Unit, primaryID: UInt, secondaryID: UInt) {
        bazCallbacks.add(Baz(callback, primaryID, secondaryID))
    }

    fun receiveEncodedData() {
        val testMessage = Bar(primaryID = 0u,
                              secondaryID = 0u)

        bazCallbacks.filter {
            if ((it.primaryID != null) && (it.secondaryID != null)) {
                (it.primaryID!! == testMessage.primaryID) && (it.secondaryID!! == testMessage.secondaryID)
            } else if ((it.primaryID == null) && (it.secondaryID != null)) {
                (it.secondaryID!! == testMessage.secondaryID)
            } else if ((it.primaryID != null) && (it.secondaryID == null)) {
                (it.primaryID!! == testMessage.primaryID)
            } else {
                true
            }
        }.forEach {
            it.callback?.invoke(
                testMessage.primaryID,
                testMessage.secondaryID
            )
        }
    }
}
