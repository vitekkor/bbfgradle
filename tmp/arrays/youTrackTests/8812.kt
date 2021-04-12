// Original bug: KT-18710

interface Listener {
        val order: Int;
    
        companion object {
            private var calls = 0;
            val order = calls++;
            val implementation = object : Listener {
                override val order = calls++; // threw a java.lang.ExceptionInInitializerError 
            }
        }
}
