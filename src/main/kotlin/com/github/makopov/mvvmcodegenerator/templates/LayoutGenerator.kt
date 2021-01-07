package com.github.makopov.mvvmcodegenerator.templates

import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.extractLetters


fun getLayoutCode(
        packageName: String,
        entityName: String) = """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${packageName}.${entityName}Fragment">

</androidx.constraintlayout.widget.ConstraintLayout>
"""