package org.jetbrains.kotlin.hover

import org.eclipse.jdt.internal.ui.text.java.hover.JavadocHover
import org.eclipse.jface.internal.text.html.BrowserInformationControlInput
import org.eclipse.jface.text.IInformationControlCreator
import org.jetbrains.kotlin.ui.editors.KotlinEditor
import org.jetbrains.kotlin.ui.editors.hover.HoverData
import org.jetbrains.kotlin.ui.editors.hover.KotlinEditorTextHover
import org.jetbrains.kotlin.core.model.KotlinAnalysisFileCache
import org.jetbrains.kotlin.core.references.getReferenceExpression
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.core.resolve.KotlinAnalyzer

class ExpressionTypeHover : KotlinEditorTextHover<BrowserInformationControlInput> {
    override fun getHoverControlCreator(editor: KotlinEditor): IInformationControlCreator? {
        return JavadocHover.PresenterControlCreator(editor.javaEditor.getSite())
    }

    override fun getHoverInfo(hoverData: HoverData): BrowserInformationControlInput? {
        val (element, editor) = hoverData
        
        val expression = PsiTreeUtil.getNonStrictParentOfType(element, KtExpression::class.java) ?: return null
        
        val ktFile = editor.parsedFile ?: return null
        val analysisResult = KotlinAnalyzer.analyzeFile(ktFile).analysisResult
        
        val type = analysisResult.bindingContext.getType(expression) ?: return null
        
        val content = "Type is <b>$type</b>"
        
        return object : BrowserInformationControlInput(null) {
            override fun getInputName(): String = "Type Name"
            override fun getHtml(): String = content
            override fun getInputElement(): Any = content
        }
    }

    override fun isAvailable(hoverData: HoverData): Boolean {
        return hoverData.editor.isScript
    }
}