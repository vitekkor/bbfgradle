// Original bug: KT-32500

// Objective-C headers generated
interface SimpleInterface
interface GenericInterface<T>
class ClassWithInterface : SimpleInterface
class ClassWithInterfaceDelegate(val v: SimpleInterface) : SimpleInterface by v
class ClassWithGenericInterfaceDelegate(val v: GenericInterface<String>) : GenericInterface<String> by v

// Objective-C headers not generated
interface CustomList<T> : List<T>
interface CustomMap<K, V> : Map<K, V>
class ClassWithListDelegate(val v: List<String>) : List<String> by v
class ClassWithCustomListDelegate(val v: CustomList<String>) : CustomList<String> by v
class ClassWithMapDelegate(val v: Map<String, String>) : Map<String, String> by v
class ClassWithCustomMapDelegate(val v: CustomMap<String, String>) : CustomMap<String, String> by v
