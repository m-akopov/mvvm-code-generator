package com.github.makopov.mvvmcodegenerator.templates

fun getViewState(
        packageName: String,
        entityName: String,

) = """
package $packageName

import com.app3null.basestructure.models.DisposableValue


data class ${entityName}ViewState(
    val showLoader: DisposableValue<Boolean>? = null,
    val showError: DisposableValue<Throwable>? = null
)

"""