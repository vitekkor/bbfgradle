// Original bug: KT-25576

annotation class NotAnymoreExperimental

@NotAnymoreExperimental
interface AnymoreFaceA

@NotAnymoreExperimental
interface AnymoreFaceB

@NotAnymoreExperimental
interface AnymoreClass : AnymoreFaceA, AnymoreFaceB

@Suppress("MISPLACED_TYPE_PARAMETER_CONSTRAINTS")
class AnymoreUsage<T: @UseExperimental(NotAnymoreExperimental::class) AnymoreFaceA>
    : @UseExperimental(NotAnymoreExperimental::class) AnymoreFaceB
    where T : @UseExperimental(NotAnymoreExperimental::class) AnymoreFaceB {
    fun useAnymore(p: T) {
        val v1 = p is @UseExperimental(NotAnymoreExperimental::class) AnymoreClass
        val v2 = p as @UseExperimental(NotAnymoreExperimental::class) AnymoreClass
    }

    @UseExperimental(NotAnymoreExperimental::class) // Warning only here.
    fun justCompare() {}
}
