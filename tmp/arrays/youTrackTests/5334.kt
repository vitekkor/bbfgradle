// Original bug: KT-23392

abstract class Base(
    val x: Int,
    val y: Int
)

interface IFoo

class Derived(
    x: Int,
    y: Int
) : Base(
    x, // indent level: 1
    y
), 
    IFoo // indent level: 1
