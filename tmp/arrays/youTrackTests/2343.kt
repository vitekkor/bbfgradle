// Original bug: KT-42130

interface PsiElement
interface KtClassOrObject : PsiElement

fun KtClassOrObject.isObjectLiteral(): Boolean = true
fun <T> runReadAction(action: () -> T): T = action()

fun foo(element: PsiElement): String {
    return when (element) {
        is KtClassOrObject -> when {
            runReadAction { element.isObjectLiteral() } -> "1"
            else -> "2"
        }
        else -> "3"
    }
}
