// Original bug: KT-25861

annotation class A1(@get:JvmStatic val x: Int)
annotation class A2(@get:Synchronized val x: Int = 1)
annotation class A3(@get:Throws(Exception::class) val x: Int = 1)
