This is an example of the eclipse plugin with Kotlin hover extension.

This plugin simply shows type information about any expression located in a script file.

Description of the plugin dependencies:

 * `org.jetbrains.kotlin.ui` - contains description of the extension point

 * `org.jetbrains.kotlin.bundled-compiler`,
   `org.jetbrains.kotlin.core` - contain classes needed to analyze Kotlin file and retrieve type information of expression

 * `org.eclipse.jface.text`,
   `org.eclipse.jdt.ui` - contain classes to present a hover popup


To create Kotlin hover extension you should implement [`KotlinEditorTextHover`](https://github.com/JetBrains/kotlin-eclipse/blob/master/kotlin-eclipse-ui/src/org/jetbrains/kotlin/ui/editors/hover/KotlinTextHover.kt#L82) interface which is similar to javas ITextHover(Extension).
 - `getHoverControlCreator` - returns the hover control creator (see ITextHoverExtension)
 - `getHoverInfo` - returns the hover information (see ITextHoverExtension2)
 - `isAvailable` - returns availability to show hover popup on concrete element and editor

For more examples you could check:
- [Javadoc Text Hover](https://github.com/JetBrains/kotlin-eclipse/blob/master/kotlin-eclipse-ui/src/org/jetbrains/kotlin/ui/editors/hover/KotlinJavadocTextHover.kt)
- [Problem Text Hover](https://github.com/JetBrains/kotlin-eclipse/blob/master/kotlin-eclipse-ui/src/org/jetbrains/kotlin/ui/editors/hover/KotlinProblemTextHover.kt)
