// Original bug: KT-34789

interface SomeInteface{
    val name: String
    val metricName: String
}

object Blah :SomeInteface {
    override val name: String = "blah"
    override val metricName: String = "stats.$name"
}

fun main(){
    println("${Blah.metricName} ${Blah.name}")
}
