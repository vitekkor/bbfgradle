// Original bug: KT-11926

    fun stringSwitch(s: String) : Int {
        when(s) {
            "ABCDEFG1" -> return 1
            "ABCDEFG2" -> return 2
            "ABCDEFG2" -> return 3
            else -> return -1
         }
     }
