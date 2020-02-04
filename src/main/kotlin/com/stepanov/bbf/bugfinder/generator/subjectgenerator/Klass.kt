package com.stepanov.bbf.bugfinder.generator.subjectgenerator

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import java.util.*

class Klass(): Expression() {

    fun generate(modifier: String): PsiElement {
        //Mb type with bound
        val typeParam = if (Random().nextBoolean()) "<T>" else ""
        val ident = Random().getRandomVariableName()
        val constructor = Constructor(typeParam.substringAfter('<').substringBefore('>'))
        //Delegation
        //Type parameter bounds? () where T: Number
        println("class ${ident.capitalize()}<$typeParam> $constructor {}")
        return factory.createClass("class ${ident.capitalize()}$typeParam $constructor {}")
    }

//    fun generateConstructor(): KtPrimaryConstructor {
//        return const
//    }
}

//class DataClass: Class() {
//    override fun generate(): PsiElement {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//class EnumClass: Class() {
//    override fun generate(): PsiElement {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//class AnnotationClass: Class() {
//    override fun generate(): PsiElement {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//class SealedClass: Class() {
//    override fun generate(): PsiElement {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class InnerClass: Class() {
//    override fun generate(): PsiElement {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//}


