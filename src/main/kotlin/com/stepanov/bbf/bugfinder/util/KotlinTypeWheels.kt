package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.resolve.source.PsiSourceElement

fun List<TypeParameterDescriptor>.sortedByTypeParams(): List<TypeParameterDescriptor> {
    return this.sortedWith(typeParamDescComparator)
}


private val typeParamDescComparator = Comparator { t1: TypeParameterDescriptor, t2: TypeParameterDescriptor ->
    if (t1.upperBounds.isEmpty() && t2.upperBounds.isEmpty()) {
        0
    } else {
        val t1Args = t1.upperBounds.flatMap { listOf("$it") + it.getAllTypeArgs().map { "${it.type}" } }
        if (t1Args.any { it == "${t2.defaultType}" }) 1 else -1
    }
}

fun DeclarationDescriptor.findPsi(): PsiElement? {
    val psi = (this as? DeclarationDescriptorWithSource)?.source?.getPsi()
    return if (psi == null && this is CallableMemberDescriptor && kind == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
        overriddenDescriptors.mapNotNull { it.findPsi() }.firstOrNull()
    }
    else {
        psi
    }
}

fun SourceElement.getPsi(): PsiElement? = (this as? PsiSourceElement)?.psi

//fun TypeParameterDescriptor.getAllTypeArgs(): List<KotlinType> {
//    val res = mutableListOf<KotlinType>()
//    res.add(this.defaultType)
//    this.upperBounds.forEach
//}