// Original bug: KT-19118

import java.io.FileInputStream
import java.util.*

object TestPropertyRead {
    @JvmStatic fun main(args: Array<String>) {

        val properties = Properties()

        try {
            FileInputStream(System.getenv("path_to_property_file")).use {
                properties.load(it)
                val test = properties.getProperty("property_name").toInt()
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }
}

