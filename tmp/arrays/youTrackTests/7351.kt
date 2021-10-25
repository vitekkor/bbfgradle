// Original bug: KT-27633

interface IType {

}

sealed class Member(name: String, val referencedTypes: List<IType>) {

}

sealed class Reactive(name: String, vararg val genericParams: IType) {
    sealed class Stateful(name: String, vararg genericParams: IType) : Reactive(name, *genericParams) {

    }
}
