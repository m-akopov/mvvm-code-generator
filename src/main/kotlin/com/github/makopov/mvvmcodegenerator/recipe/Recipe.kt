package com.github.makopov.mvvmcodegenerator.recipe

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.makopov.mvvmcodegenerator.listeners.MyProjectManagerListener.Companion.projectInstance
import com.github.makopov.mvvmcodegenerator.templates.*
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage

fun RecipeExecutor.mviSetup(
        moduleData: ModuleTemplateData,
        packageName: String,
        entityName: String,
        layoutName: String
) {

    println("Akopovvv  RecipeExecutor.mviSetup")
    val (projectData) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("src") }
    val virtRes = virtualFiles.first { it.path.contains("res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    getFragmentCode(packageName, entityName)
            .save(directorySrc, packageName, "${entityName}Fragment.kt")

    getViewState(packageName, entityName)
            .save(directorySrc, packageName, "${entityName}ViewState.kt")


    getViewModelCode(packageName, entityName)
            .save(directorySrc, packageName, "${entityName}ViewModel.kt")

    getShowLoaderActionCode(packageName, entityName)
            .save(directorySrc, "$packageName.actions", "ShowLoaderAction.kt")

    getShowErrorStateCode(packageName, entityName)
            .save(directorySrc, "$packageName.actions", "ShowErrorAction.kt")

    getLayoutCode(packageName, entityName)
            .save(directoryRes, "layout", "${layoutName}.xml")


}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
                .getInstance(srcDir.project)
                .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    }catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}