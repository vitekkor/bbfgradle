// Original bug: KT-19601

interface Order<T>

typealias Ord<T> = Order<T>

class Container1<T : Ord<T>>
// ^ Error:(7, 26) Kotlin: Type argument is not within its bounds: should be subtype of 'Any?'

class Container2<T : Order<T>>
