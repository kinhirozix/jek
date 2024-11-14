package jek.idea.inspection

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.util.isExpectDeclaration
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class ExpectActualAllowedInJvmInspection : LocalInspectionTool() {
    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        if (file is KtFile) {
            val problems = mutableListOf<ProblemDescriptor>()
            file.declarations.forEach { declaration ->
                when (declaration) {
                    is KtNamedFunction -> {
                        if (declaration.isExpectDeclaration()) {
                        }
                    }

                    is KtClassOrObject -> {
                        if (declaration.isExpectDeclaration()) {
                        }
                    }
                }
            }

            return problems.toTypedArray()
        }

        return ProblemDescriptor.EMPTY_ARRAY
    }
}
