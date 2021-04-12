// Original bug: KT-35635

package pppp
abstract class U
class UU : U()
class UUU : U()


open class Scope
class UUUScope : Scope()



class ScopeSession {

    val scopes = hashMapOf<U, Scope>()

    inline fun <reified ID : U, reified FS : Scope> getOrBuild(id: ID, build: () -> FS): FS {
        return scopes.getOrPut(id) {
            build()
        } as FS
    }
}

fun scope(u: U, session: ScopeSession): Scope? {
    return when(u) {
        is UU -> Scope()
        is UUU -> session.getOrBuild(u) {
            UUUScope()
        } // adding 'as Scope' fixes problem
        else -> null
    }
}

fun main() {
    println(scope(UUU(), ScopeSession()))
}
