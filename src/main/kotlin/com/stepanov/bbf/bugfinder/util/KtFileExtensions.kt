package com.stepanov.bbf.bugfinder.util

import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile

fun KtFile.getClassOfName(name: String): KtClassOrObject? =
    this.getAllPSIChildrenOfType<KtClassOrObject>().find { it.name == name }