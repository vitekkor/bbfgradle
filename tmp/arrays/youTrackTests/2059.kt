// Original bug: KT-14219

    object A {
        
        @JvmStatic
        val i=1
        
        @JvmStatic
        fun b()=Unit

        @JvmStatic
        fun c()=Unit
    }
