// Original bug: KT-40145

interface FirClass<F : FirClass<F>> : FirStatement

interface FirStatement : FirAnnotationContainer

interface FirControlFlowGraphOwner : FirElement

interface FirAnnotationContainer : FirElement

interface FirElement {
    fun foo(s: String) = s
}

abstract class FirPureAbstractElement : FirElement

abstract class FirExpression : FirPureAbstractElement(), FirStatement

abstract class FirAnonymousObject :
    FirClass<FirAnonymousObject>,
    FirControlFlowGraphOwner,
    FirExpression()
