// Original bug: KT-29834

import java.util.*

fun main(args: Array<String>){

    println("testing. .. ")

    var cal = Calendar.getInstance()

    cal.set(Calendar.YEAR,2018)
    cal.set(Calendar.MONTH, 8)
    cal.set(Calendar.DAY_OF_YEAR,17)

    println(cal.time)

}