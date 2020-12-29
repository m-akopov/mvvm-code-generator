package com.github.makopov.mvvmcodegenerator.templates

fun getShowErrorStateCode(
        packageName: String,
        entityName: String
) = """
package $packageName.actions

import com.app3null.basestructure.actions.ViewStateAction
import com.app3null.basestructure.models.DisposableValue
import $packageName.${entityName}ViewState

class ShowErrorAction(private val throwable: Throwable) : ViewStateAction<${entityName}ViewState> {
    override fun newState(oldState: ${entityName}ViewState): ${entityName}ViewState {
        return oldState.copy(showError = DisposableValue(throwable))
    }
}

"""

fun getShowLoaderActionCode(
        packageName: String,
        entityName: String
) = """
package $packageName.actions

import com.app3null.basestructure.actions.ViewStateAction
import com.app3null.basestructure.models.DisposableValue
import $packageName.${entityName}ViewState

class ShowLoaderAction(private val throwable: Throwable) : ViewStateAction<${entityName}ViewState> {
    override fun newState(oldState: ${entityName}ViewState): ${entityName}ViewState {
        return oldState.copy(showLoader = DisposableValue(true))
    }
}

"""