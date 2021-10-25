// Original bug: KT-24267

    class Outer {
        inner class Inner(val v: Int = 42)
    }

    fun F()
    {
        val constructor = Outer.Inner::class.constructors.first()
        val outerParam = constructor.parameters[0]
        val outer = Outer()
        constructor.callBy(mapOf(outerParam to outer))
        // This works as expected:
        // constructor.call(outer, 43)
    }
