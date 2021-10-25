// Original bug: KT-36143

fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> = HashMap(this)

@Suppress("unused")
interface StringAttribute<Value : Any>

interface StringAttributeMap{}

private class StringAttributeHashMap(
	internal val map: HashMap<StringAttribute<*>, Any>
) : StringAttributeMap {}

private class SingleElementStringAttributeMap(
	
) : StringAttributeMap {}

fun stringAttributesOf(): StringAttributeMap =
	SingleElementStringAttributeMap()

private object EmptyStringAttributeMap : StringAttributeMap{}

fun Array<out Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	when (size) {
		0 -> EmptyStringAttributeMap
		1 -> SingleElementStringAttributeMap()
		else -> StringAttributeHashMap(hashMapOf(*this))
	}

