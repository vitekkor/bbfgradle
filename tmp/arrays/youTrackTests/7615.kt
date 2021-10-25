// Original bug: KT-22922

@Experimental
annotation class FirstExperience

open class ParentTarget {
    @FirstExperience open fun targetFun() {}
}

class ChildTarget : ParentTarget() 