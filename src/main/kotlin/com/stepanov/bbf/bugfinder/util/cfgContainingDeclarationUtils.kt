package com.stepanov.bbf.bugfinder.util

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptorWithResolutionScopes
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getNonStrictParentOfType
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorUtils
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

val KtElement.containingDeclarationForPseudocode: KtDeclaration?
    get() = PsiTreeUtil.getParentOfType(
        this,
        KtDeclarationWithBody::class.java,
        KtClassOrObject::class.java,
        KtScript::class.java
    )
        ?: getNonStrictParentOfType<KtProperty>()

// Should return KtDeclarationWithBody, KtClassOrObject, or KtClassInitializer
fun KtElement.getElementParentDeclaration(): KtDeclaration? =
    PsiTreeUtil.getParentOfType(
        this,
        KtDeclarationWithBody::class.java,
        KtClassOrObject::class.java,
        KtClassInitializer::class.java
    )

fun KtDeclaration?.getDeclarationDescriptorIncludingConstructors(context: BindingContext): DeclarationDescriptor? {
    val descriptor = context.get(BindingContext.DECLARATION_TO_DESCRIPTOR, (this as? KtClassInitializer)?.containingDeclaration ?: this)
    return if (descriptor is ClassDescriptor && this is KtClassInitializer) {
        // For a class primary constructor, we cannot directly get ConstructorDescriptor by KtClassInitializer,
        // so we have to do additional conversion: KtClassInitializer -> KtClassOrObject -> ClassDescriptor -> ConstructorDescriptor
        descriptor.unsubstitutedPrimaryConstructor
            ?: (descriptor as? ClassDescriptorWithResolutionScopes)?.scopeForInitializerResolution?.ownerDescriptor
    } else {
        descriptor
    }
}

//@OptIn(ExperimentalContracts::class)
//fun DeclarationDescriptor.isSealed(): Boolean {
//    contract {
//        returns(true) implies (this@isSealed is ClassDescriptor)
//    }
//    return DescriptorUtils.isSealedClass(this)
//}