// Original bug: KT-43430

import Platform.*
import ServerType.*

enum class Platform {
    PROXY, SERVER
}

enum class ServerType {
    LOBBY, GAME, OTHER
}

data class Server(
        val platform: Platform
) {
    var serverType: ServerType? = null
}

class ServerService {

    fun register(platform: Platform, data: Map<String, String>): Server {
        return when (platform) {
            PROXY -> Server(platform)
            SERVER -> Server(platform).also {
                it.serverType = if (data.containsKey("serverType")) ServerType.valueOf(data.getValue("serverType")) else OTHER 
            }
        }
    }
}
