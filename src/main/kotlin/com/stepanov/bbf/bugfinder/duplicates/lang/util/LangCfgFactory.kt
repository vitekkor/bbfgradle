//Created by Anastasiya Shadrina (github.com/shadrina)

package com.stepanov.bbf.bugfinder.duplicates.lang.util

import com.stepanov.bbf.bugfinder.duplicates.lang.LangCfg
import com.stepanov.bbf.bugfinder.duplicates.lang.groovy.GroovyCfg
import com.stepanov.bbf.bugfinder.duplicates.lang.java.JavaCfg
import com.stepanov.bbf.bugfinder.duplicates.lang.kotlin.KotlinCfg

object LangCfgFactory {
    fun createLangCfg(fileExtension: String) : LangCfg? {
        return when (fileExtension) {
            "java" -> JavaCfg()
            "kt" -> KotlinCfg()
            "groovy" -> GroovyCfg()
            else -> null
        }
    }
}