// Original bug: KT-17740

interface Contract {
    fun oblige(): Int
}
open class ParentContractImpl : Contract {
    override fun oblige() = 1
}
class ContractImpl : Contract {
    override fun oblige() = 0
} 