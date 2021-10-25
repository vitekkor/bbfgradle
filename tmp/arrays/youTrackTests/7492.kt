// Original bug: KT-25315

interface Publisher {
    fun getMessage(): String
}

inline class DefaultPublisher(private val messageToPublish: String) : Publisher {
    override fun getMessage() = messageToPublish
}

class SomethingThatPublishes : Publisher by DefaultPublisher("Hello")
