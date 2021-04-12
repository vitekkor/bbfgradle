// Original bug: KT-44091

interface IBase

interface ISub : IBase
// see the comment later
//interface ISub: Serializable      
//interface ISub: Comparable<ISub>

class A : ISub

class B : ISub

enum class C : ISub { C1, C2 }
enum class D : ISub { D1, D2 }

// expected List<ISub>, OK
// use array because Enum.values() returns Array<T>
private val normalInference = arrayOf(A(), A()).toList().plus(arrayOf(B(), B()))

// expected List<ISub>, But List<Any>, neither List<ISub> nor List<IBase>
// If ISub extends Serializable, or other interfaces XXX other than IBase, then it is List<XXX>, but not List<ISub>
private val enumInference = C.values().toList().plus(D.values())

//Works but why?
private val explicit =  C.values().toList().plus<ISub>(D.values())
