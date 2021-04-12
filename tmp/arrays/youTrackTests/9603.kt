// Original bug: KT-4764

package cz.novak.sample

import java.io.File
import java.io.IOException
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import org.apache.commons.io.IOUtils

class VarNotInitialized() {


    public fun sample(): String? {
        try {
            if (false) {
                return "fail"
            } else {
                if (false) {
                    return "fail"
                } else {
                    var result = ByteArrayOutputStream()
                    var file = File("c:/myFile.txt")
                    if (file.exists() && file.isFile()) {
                        var fileInputStream: FileInputStream? = null
                        try {
                            fileInputStream = FileInputStream(file)
                            IOUtils.copyLarge(fileInputStream, result)
                        } catch (e: IOException) {
                            return "fail"
                        } finally {
                            IOUtils.closeQuietly(fileInputStream)
                        }
                    }
                    return "fail"
                }
            }
        } catch (error: IOException) {
            return "fail"
        } catch (error: Exception) {
            return "fail"
        } finally {
        }
    }

}