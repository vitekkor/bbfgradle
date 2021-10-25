// Original bug: KT-15348

fun minWays(amount: Int, coins: IntArray): Int {
    val table = IntArray(amount + 1)
    for (money in 1..amount) {      
        table[money] = coins  // .asSequence() here if GC maters 
        				.filter {it <= money}
        				.map {coin -> table[money - coin] + 1}
        				.min()!!
    }
    println(table.toString())
    return table[amount]
}
