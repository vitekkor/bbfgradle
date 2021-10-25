// Original bug: KT-35859

import org.intellij.lang.annotations.Language

fun makeACall() {
    check(
            compatibilityMode = false,
            sourceFiles = arrayOf(),
            api = """
                package @RestrictTo(androidx.annotation.RestrictTo.Scope.SUBCLASSES) @RestrictTo(androidx.annotation.RestrictTo.Scope.SUBCLASSES) test.pkg {
                  public abstract class Class1 {
                    ctor public Class1();
                  }
                }
                """
    )
}

private fun check(
        classpath: Array<String>? = null,
        @Language("TEXT") api: String? = null,
        @Language("XML") apiXml: String? = null,
        compatibilityMode: Boolean = true,
        sourceFiles: Array<String> = emptyArray()
) { }

