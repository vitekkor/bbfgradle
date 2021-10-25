// Original bug: KT-37436

package pack

fun interface SingleFunFaceBound {
    fun nonAbstractFun() {}
    fun abstractFun()
}

fun acceptFaceBound(sff: SingleFunFaceBound) {
    sff.nonAbstractFun() // looks essential to reproduce
}

fun main() {
    acceptFaceBound (object : SingleFunFaceBound { override fun abstractFun() {} }) // no error
    acceptFaceBound (SingleFunFaceBound {}) // error
    acceptFaceBound {} // same error after removing the line above
}
