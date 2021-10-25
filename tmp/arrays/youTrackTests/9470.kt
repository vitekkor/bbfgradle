// Original bug: KT-13374

package test

interface IZ {
    fun z()
}

interface IZZ : IZ {
    fun zz()
}

inline fun implZZ(zImpl: IZ, crossinline zzImpl: () -> Unit): IZZ =
        object : IZZ, IZ by zImpl {
            override fun zz() = zzImpl()
        }

object ZImpl : IZ {
    override fun z() {
        println("IZZ::z")
    }
}

fun main(args: Array<String>) {
    val zz = implZZ(ZImpl) { println("<impl>::zz") }
    zz.z()
    zz.zz()
}
