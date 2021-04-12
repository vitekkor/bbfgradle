// Original bug: KT-25789

var adr = 0L

class RenderPassBeginInfo

fun pickTwo(p1: Long, p2: Long) {}

inline var RenderPassBeginInfo.frameBuffer: Long
    get() = 0L
    set(value) {
        pickTwo(adr, value)
    }

class ImGui {
    val frameBuffers = ArrayList<Long>()
    fun buildCommandBuffers(rpbi: RenderPassBeginInfo) {
        rpbi.frameBuffer = frameBuffers[0]
    }
}
