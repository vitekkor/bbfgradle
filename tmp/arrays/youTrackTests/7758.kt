// Original bug: KT-25885

    fun F(): String?
    {
        run {
            var s1: String? = String.format("")
            if (s1 != null) {
                s1.hashCode()
                s1 = s1.toString()
            }
            s1
        }

        var s: String? = String.format("")
        if (s != null) {
            s.hashCode()
            s = s.toString()
        }
        return s
    }
