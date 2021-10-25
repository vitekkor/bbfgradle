// Original bug: KT-40937

import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer.PLAIN_RELATIVE_PATHS
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.openapi.util.text.LineColumn
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiErrorElement
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.psiUtil.collectDescendantsOfType
import org.jetbrains.kotlin.psi.psiUtil.startOffset

/** sdf */
fun main() {
  Parser.parse("")
}

/** Parser parses a Kotlin file given as a string and returns its parse tree. */
open class Parser {
  fun parse(code: String): KtFile {
    val disposable = Disposer.newDisposable()
    try {
      val configuration = CompilerConfiguration()
      configuration.put(
        CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY,
        PrintingMessageCollector(System.err, PLAIN_RELATIVE_PATHS, false))
      val env =
        KotlinCoreEnvironment.createForProduction(
          disposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES)
      val virtualFile = LightVirtualFile("temp.kt", KotlinFileType.INSTANCE, code)
      return PsiManager.getInstance(env.project).findFile(virtualFile) as KtFile
    } finally {
      disposable.dispose()
    }
  }

  companion object : Parser() {
    init {
      // To hide annoying warning on Windows
      System.setProperty("idea.use.native.fs.for.win", "false")
    }
  }
}
