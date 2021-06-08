package com.github.makopov.mvvmcodegenerator.templates

fun getViewModelCode(
        packageName: String,
        entityName: String
) = """
package $packageName

import com.app3null.basestructure.viewModels.BaseViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.SavedStateHandle

@HiltViewModel
class ${entityName}ViewModel @Inject constructor(
    val handle: SavedStateHandle
) : BaseViewModel<${entityName}ViewState>() {

    override fun getInitialState(): ${entityName}ViewState {
        return ${entityName}ViewState()
    }
}


"""