// Original bug: KT-25989

annotation class AuxAnna
interface AuxFaceA
interface AuxFaceB

/**
 * Very long line. Very long line. Very long line. Very long line. Very long line. Very long line. Very long line. Very long line. Very long line. Very long line.
 */
abstract class LongClassDeclaration<X : AuxFaceA, Y : AuxFaceB> @AuxAnna private constructor(val primaryA: String, val primaryB: String) : AuxFaceA, AuxFaceB where X : AuxFaceB, Y : AuxFaceA {}
