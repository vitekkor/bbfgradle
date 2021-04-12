// Original bug: KT-23755

sealed class PossibleData<T> {
    data class Data<T>(val data: T) : PossibleData<T>()
    class NoData<T> : PossibleData<T>()
}

class Wrapper<T>(val item : T)

fun <I, O> Wrapper<PossibleData<List<I>>>.map(converter : (Wrapper<PossibleData<List<I>>>) -> Wrapper<PossibleData<List<O>>>) : Wrapper<PossibleData<List<O>>> {
    TODO()
}

fun main() {
    val intList = emptyList<Int>()

    val originalB : Wrapper<PossibleData<List<Int>>> = Wrapper(PossibleData.Data(intList))

    originalB.map { originalHolder ->
        Wrapper(originalHolder.item.let {resource ->
            if (resource is PossibleData.Data) {
                PossibleData.Data(emptyList<String>()) as PossibleData<List<String>>
            } else {
                throw Exception()
            }
        })
    }
}
