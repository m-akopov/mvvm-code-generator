package com.github.makopov.mvvmcodegenerator.static_shit

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.makopov.mvvmcodegenerator.templates.mviSetupTemplate

class WizardTemplateProviderImpl  : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(mviSetupTemplate)
}