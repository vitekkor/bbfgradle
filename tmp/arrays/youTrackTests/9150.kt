// Original bug: KT-16127

fun CharSequence.withReplace(startOffset: Int, endOffset: Int, replacementText: CharSequence): CharSequence
{
    return "${subSequence(0,startOffset)}$replacementText${subSequence(endOffset,length)}" as CharSequence
}
