package com.github.makopov.mvvmcodegenerator.templates

import com.android.tools.idea.wizard.template.*
import com.github.makopov.mvvmcodegenerator.recipe.mviSetup
import java.io.File

val mviSetupTemplate
    get() = template {
        revision = 1
        name = "APP3NULL MVVM Generator"
        description = "Generates MVVM structure for a screen."
        minApi = 16
        minBuildApi = 16
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
                WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = ""
            help = "The name of the entity class to create and use as a Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "The name of the layout to create for the fragment"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(entityName.value.toLowerCase()) }
        }

        println("Recipeeeeee Akopov MIsha")

        widgets(
                TextFieldWidget(entityName),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mviSetup(
                    data as ModuleTemplateData,
                    packageNameParam.value,
                    entityName.value.capitalize(),
                    layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}