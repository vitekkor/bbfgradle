// Original bug: KT-32423


import java.time.DayOfWeek
import java.time.LocalDateTime

class Y {
    fun x() {
        val foo = if(LocalDateTime.now().dayOfWeek == DayOfWeek.WEDNESDAY) mutableListOf<String>() else null
        val bar = foo ?: listOf()
    }
}

