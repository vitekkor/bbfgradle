// Original bug: KT-34183

import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.*
import java.util.stream.Stream
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestTemplateInvocationContext
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider


@TestTemplate
@ExtendWith(MyTestTemplateInvocationContextProvider::class)
annotation class Retry()

class MyTestTemplateInvocationContextProvider : TestTemplateInvocationContextProvider {

    override fun supportsTestTemplate(context: ExtensionContext): Boolean {
        return true
    }

    override fun provideTestTemplateInvocationContexts(
        context: ExtensionContext
    ): Stream<TestTemplateInvocationContext> {

        return Stream.of(object : TestTemplateInvocationContext {})
    }
}
