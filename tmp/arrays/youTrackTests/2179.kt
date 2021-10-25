// Original bug: KT-39086

class Clazz

suspend fun setTask(): Unit? {
    var newTask: Clazz? = null
    newTask?.let { sendTask(it) }

    try {
        try {
            if (true) {
                return null
            }
        } catch (ex: Exception) {

        } finally {

        }

        try {
            if (true) {
                return null
            }
        } catch (ex: Exception) {

        } finally {

        }
    } catch (ex: Exception) {

    } finally {

    }

    return null
}

suspend fun sendTask(clazz: Clazz): Clazz {
    return TODO()
}
