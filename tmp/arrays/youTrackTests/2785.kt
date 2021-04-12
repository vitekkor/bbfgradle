// Original bug: KT-40389

interface InterfaceOne

interface InterfaceTwo : InterfaceOne

open class ClassOne : InterfaceTwo

open class ClassTwo : ClassOne()

class ClassThree : ClassTwo()
