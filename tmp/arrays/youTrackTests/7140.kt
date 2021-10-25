// Original bug: KT-12415

import java.util.concurrent.CopyOnWriteArrayList

open class Vm

open class VmConnection<T : Vm>

class RemoteVmConnection : VmConnection<Vm>()

interface BaseIntf {
    val childConnections: List<VmConnection<*>>
        get() = emptyList()
}

abstract class BaseImpl<C : VmConnection<*>> : BaseIntf {
    override final val childConnections = CopyOnWriteArrayList<C>()
}
