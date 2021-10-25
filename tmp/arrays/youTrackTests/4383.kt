// Original bug: KT-36480

annotation class ColorAnn
interface Face
open class Parent
class CaseA : @ColorAnn Face, @ColorAnn Parent()
class CaseB : @ColorAnn Parent(), @ColorAnn Face
