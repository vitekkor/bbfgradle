// Original bug: KT-3114

import java.util.Date

fun getDate(): Date? = Date()

fun test() {
    var date1: Date? = getDate()
    var date2: Date? = getDate()

    if (date1 != null && date2 != null){
        println(date1!! > date2!!)
    }
}

fun main(args: Array<String>) {
    test()
}
