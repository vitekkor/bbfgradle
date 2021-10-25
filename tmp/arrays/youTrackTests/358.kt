// Original bug: KT-45275

fun isFirstComeFirstServed(takeOutOrders: IntArray, dineInOrders: IntArray, servedOrders: IntArray): Boolean {
    var takeOutIndex = 0
    var daneInIndex = 0

    for (order in servedOrders) { // use withIndexed()
        
        val takeOutValue = takeOutOrders.getOrNull(takeOutIndex)
        val dineInValue = takeOutOrders.getOrNull(takeOutIndex)

        takeOutIndex++
        daneInIndex++
    }

    return true
}
