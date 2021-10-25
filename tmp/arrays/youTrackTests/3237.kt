// Original bug: KT-37891

annotation class A

class Foo {
    @A
    lateinit var logger: String
    @A
    lateinit var userService: String
    @A
    lateinit var configBridge: String
}
