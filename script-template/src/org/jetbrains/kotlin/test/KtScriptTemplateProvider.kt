package org.jetbrains.kotlin.test

import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Platform
import org.jetbrains.kotlin.script.KotlinScriptExternalDependencies
import org.jetbrains.kotlin.script.ScriptContents
import org.jetbrains.kotlin.script.ScriptDependenciesResolver
import org.jetbrains.kotlin.script.ScriptTemplateDefinition
import org.jetbrains.kotlin.script.ScriptTemplatesProvider
import org.jetbrains.kotlin.script.asFuture
import java.io.File
import java.util.concurrent.Future

class KtScriptTemplateProvider : ScriptTemplatesProvider {
    override val dependenciesClasspath: Iterable<String>
        get() {
            val pluginBundle = Platform.getBundle(Activator.PLUGIN_ID)
            val pluginOutput = FileLocator.toFileURL(pluginBundle.getEntry("/bin"))

            return listOf(pluginOutput.getFile())
        }

    override val environment: Map<String, Any?>?
        get() = emptyMap()

    override val id: String
        get() = "Test"

    override val isValid: Boolean
        get() = true

    override val templateClassNames: Iterable<String>
        get() = listOf("org.jetbrains.kotlin.test.TestScriptTemplateDefinition")

    override val version: Int
        get() = 10
}

@ScriptTemplateDefinition(
        resolver = TestKotlinScriptResolver::class,
        scriptFilePattern = ".*\\.my\\.kts"
)
open class TestScriptTemplateDefinition(val firstParam: String) {
    fun callFromBase(y: Int) {
        println(y)
    }
}

fun TestScriptTemplateDefinition.testExtension(x: Int): String {
    return x.toString()
}

class TestKotlinScriptResolver : ScriptDependenciesResolver {
    override fun resolve(script: ScriptContents,
                         environment: Map<String, Any?>?,
                         report: (ScriptDependenciesResolver.ReportSeverity, String, ScriptContents.Position?) -> Unit,
                         previousDependencies: KotlinScriptExternalDependencies?): Future<KotlinScriptExternalDependencies?> {
        return TestScriptExternalDependencies.asFuture()
    }
}

object TestScriptExternalDependencies : KotlinScriptExternalDependencies {
    override val classpath: Iterable<File>
        get() = listOf()

    override val sources: Iterable<File>
        get() = listOf()

    override val imports: Iterable<String>
        get() = listOf(
                "java.io.File",
                "java.util.concurrent.*",
                "org.jetbrains.kotlin.test.*")
}