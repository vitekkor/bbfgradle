// Original bug: KT-19979

data class ThingsData(val things: List<Thing>) 
{
  // synthetic property
  val hasTrueThing get() = things.firstOrNull()?.field == true
}

data class Thing(val field: Boolean?)

fun main(args: Array<String>) {
    val testData = ThingsData(things = listOf(Thing(null)))
    println(testData.hasTrueThing) // <-- crashes here
}
