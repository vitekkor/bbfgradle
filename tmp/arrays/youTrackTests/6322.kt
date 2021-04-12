// Original bug: KT-270

        val a: java.lang.String = java.lang.String("OK")
        val b: String = a as String  //Valid and Necessary cast.

        val i: Int = 10
        val j: java.lang.Integer = i as java.lang.Integer //Valid and Necessary cast.
