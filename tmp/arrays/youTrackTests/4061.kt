// Original bug: KT-17048

package test

interface DeclarationDescriptor
interface ParameterDescriptor : DeclarationDescriptor
interface VariableDescriptor : DeclarationDescriptor

interface IrSymbolOwner
interface IrValueParameter : IrSymbolOwner
interface IrVariable : IrSymbolOwner

interface IrSymbol

interface IrBindableSymbol<out D : DeclarationDescriptor, B : IrSymbolOwner> : IrSymbol
interface IrValueSymbol : IrSymbol
interface IrValueParameterSymbol : IrValueSymbol, IrBindableSymbol<ParameterDescriptor, IrValueParameter>
interface IrVariableSymbol : IrValueSymbol, IrBindableSymbol<VariableDescriptor, IrVariable>

class Remapper {
    val valueParameters = HashMap<IrValueParameterSymbol, IrValueParameterSymbol>()
    val variables = HashMap<IrVariableSymbol, IrVariableSymbol>()

    fun getReferencedValue(symbol: IrValueSymbol): IrValueSymbol =
            when (symbol) {
                is IrValueParameterSymbol -> valueParameters[symbol]
                is IrVariableSymbol -> variables[symbol]
                else -> throw IllegalArgumentException("Unexpected symbol $symbol")
            }  ?: symbol
}
