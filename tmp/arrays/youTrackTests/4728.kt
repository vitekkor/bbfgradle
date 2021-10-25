// Original bug: KT-35635

package pppp
abstract class U
class UU : U()
class UUU : U()


open class Scope
class UUUScope : Scope()



class ScopeSession {

    inline fun <reified FS : Scope> getOrBuild(build: () -> FS): FS {
        return build() as FS // removing this cast fixes VE
    }
}


fun scope(u: U, session: ScopeSession): Scope? {
    return when(u) {
        is UU -> Scope()
        is UUU -> session.getOrBuild {
            UUUScope()
        }
        else -> null
    }
}

fun main() {
    println(scope(UUU(), ScopeSession()))
}
