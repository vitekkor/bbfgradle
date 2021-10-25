// Original bug: KT-40294

interface Kind<out F, out A>

class ForEval private constructor() { companion object }
class ForConst private constructor() { companion object }
typealias EvalOf<A> = Kind<ForEval, A>
typealias ConstPartialOf<A> = Kind<ForConst, A>

class Eval<out A> : EvalOf<A>

interface Apply<F> {
    fun <A, B> Kind<F, A>.apEval(ff: Eval<Kind<F, (A) -> B>>): Eval<Kind<F, B>> = TODO()
    // and other methods
}

interface Applicative<F> : Apply<F>

interface ConstApplicative<A> : Applicative<ConstPartialOf<A>>
