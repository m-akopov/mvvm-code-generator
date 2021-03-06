package com.github.makopov.mvvmcodegenerator.templates

fun getViewModelCode(
        packageName: String,
        entityName: String
) = """
package $packageName

import com.app3null.basestructure.viewModels.BaseViewModel

class ${entityName}ViewModel : BaseViewModel<${entityName}ViewState>() {

    override fun getInitialState(): ${entityName}ViewState {
        return ${entityName}ViewState()
    }
}


"""