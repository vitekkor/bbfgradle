package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.resolve.source.PsiSourceElement
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.isTypeParameter

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

fun KotlinType.replaceTypeArgsToTypes(map: Map<String, String>): String {
    val realType =
        when {
            isTypeParameter() -> map[this.constructor.toString()] ?: map["${this.constructor}?"] ?: this.toString()
            this.isNullable() -> "${this.constructor}?"
            else -> this.constructor.toString()
        }
    val typeParams = this.arguments.map { it.type.replaceTypeArgsToTypes(map) }
    return if (typeParams.isNotEmpty()) "$realType<${typeParams.joinToString()}>" else realType
}

//fun TypeParameterDescriptor.getAllTypeArgs(): List<KotlinType> {
//    val res = mutableListOf<KotlinType>()
//    res.add(this.defaultType)
//    this.upperBounds.forEach
//}