package com.github.makopov.mvvmcodegenerator.services

import com.intellij.openapi.project.Project
import com.github.makopov.mvvmcodegenerator.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
