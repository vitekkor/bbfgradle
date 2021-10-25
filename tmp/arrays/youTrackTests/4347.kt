// Original bug: KT-36446

import kotlin.experimental.ExperimentalTypeInference
import kotlin.reflect.KProperty

fun main() {
    definition<Transaction> {
        conversion<Unit> {
            val offset by argument<Int> {
                // UnsupportedOperationException: no descriptor for type constructor of IntegerLiteralType[Int,Long,Byte,Short]
                defaultInt(0)
            }
        }
    }
}

interface Transaction

fun <Transaction> definition(configure: DefinitionBuilder<Transaction>.() -> Unit): Unit = TODO()

class ArgumentBuilder<Value> {
    fun defaultInt(default: Int): Unit = TODO()
}

class ConversionBuilder<Value> {
    fun <ArgumentValue> argument(configure: ArgumentBuilder<ArgumentValue>.() -> Unit): ArgumentDefinition<ArgumentValue> = TODO()
}

class DefinitionBuilder<Transaction> {
    @UseExperimental(ExperimentalTypeInference::class)
    fun <Value> conversion(@BuilderInference configure: ConversionBuilder<Value>.() -> Unit): Unit = TODO()
}

interface ArgumentDefinition<Value> {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ArgumentReference<Value>
}

interface ArgumentReference<out Value> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Value
}
