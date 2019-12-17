package com.stepanov.bbf.reduktor.executor.backends

import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus

interface CommonBackend {
    fun tryToCompile(path: String): KotlincInvokeStatus
}