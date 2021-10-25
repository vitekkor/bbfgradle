// Original bug: KT-10809

interface ConfigurationData
interface ConfigurationListDataValue
class FlagData(val value: Boolean) : ConfigurationData
class ConfigurationListData<T : ConfigurationListDataValue>(val list: List<T>) : ConfigurationData

fun deserialize(o: Any): ConfigurationData? = when (o) { // OK
    is List<*> -> ConfigurationListData(listOf())
    is Int -> when {
        o < 0 -> FlagData(true)
        else -> null
    }
    else -> null
}
