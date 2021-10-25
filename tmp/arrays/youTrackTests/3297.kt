// Original bug: KT-39305

interface IThing<T> {}
class Thing : IThing<Set<List<Collection<Int>>>> {}

fun <T> doThing(t: IThing<T>) {}

fun reproduce() {
    // With the new type inference algorithm, error on the function invocation below:
    // "Not enough information to infer type variable T"
    doThing(Thing())
    // (No such error with the old type inference algorithm.)
}
