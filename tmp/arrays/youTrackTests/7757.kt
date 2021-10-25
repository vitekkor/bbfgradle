// Original bug: KT-25885

    fun F(): String?
    {
        run {
            var s: String? = String.format("")
            if (s != null) {
                s.hashCode()
//               ^---------------Error: Kotlin: Smart cast to 'String' is impossible, because 's' is a local variable that is captured by a changing closure
                s = s.toString()
            }
            s
        }

        var s: String? = String.format("")
        if (s != null) {
            s.hashCode()
//           ^---------------Error: Kotlin: Smart cast to 'String' is impossible, because 's' is a local variable that is captured by a changing closure
            s = s.toString()
        }
        return s
    }
