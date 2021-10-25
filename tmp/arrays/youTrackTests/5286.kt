// Original bug: KT-18772

import Platform.JvmPlatform
sealed class Platform {
    object JvmPlatform : Platform()   
    class Another(val name: String) : Platform()
}
class ModuleInfo(val platform: Platform)
fun foo(moduleInfo: ModuleInfo) = when {
    moduleInfo.platform == JvmPlatform -> 1
    else -> 0
}
