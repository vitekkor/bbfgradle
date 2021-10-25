// Original bug: KT-32138

class B(val foo: Int, val bar: Int) {
    
    class Builder {
        var foo: Int = 0
        private var bar: Int = 0

        fun mutateBar(): Builder {
            bar++
            return this
        }
        
        fun build(): B = B(foo, bar)
    }
}

private typealias ApplyRestrictions = B.Builder.() -> B.Builder

private val StandardRestrictions: ApplyRestrictions = {
    apply {
        foo = 1
    }
}

private data class BluePrint(val applyRestrictions: ApplyRestrictions)

class JobCreator {
    private fun getBluePrintForTag(tag: String): BluePrint {
        return when (tag) {
            "A" -> BluePrint(StandardRestrictions)
            else -> throw IllegalArgumentException()
        }
    }
    
    internal fun getRestrictions(tag: String) = getBluePrintForTag(tag).applyRestrictions
}

class A {

    fun buildB(tag: String, jobCreator: JobCreator): B {
        val applyRestrictions = jobCreator.getRestrictions(tag)
        
        return B.Builder()
                .applyRestrictions()
                .mutateBar()
                .build()
    }
}

