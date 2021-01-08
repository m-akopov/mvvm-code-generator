package com.github.makopov.mvvmcodegenerator.templates

import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.extractLetters

fun getFragmentCode(
        packageName: String,
        entityName: String
) = """
package $packageName

import android.os.Bundle
import com.app3null.basestructure.fragments.BaseFragment
import com.app3null.basestructure.common.extensions.notNull
import com.app3null.basestructure.dialogs.Loader
import com.app3null.common_code.extensions.showThrowableMessage
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ${"[a-z]*\\.[a-z]*\\.[a-z]*".toRegex().find(packageName)?.value}.databinding.Fragment${entityName.toLowerCase().capitalize()}Binding


class ${entityName}Fragment : BaseFragment<${entityName}ViewState, ${entityName}ViewModel>() {

    private var _binding: Fragment${entityName.toLowerCase().capitalize()}Binding? = null
    private val binding get() = _binding!!

    private val viewModel: ${entityName}ViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = Fragment${entityName.toLowerCase().capitalize()}Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun provideViewModel(): ${entityName}ViewModel {
        return viewModel
    }

    override fun reflectState(viewState: ${entityName}ViewState) {
        viewState.showLoader?.getValue().notNull {
            registerDialog(Loader.show(requireContext()))
        }

        viewState.showError?.getValue().notNull {
            dismissAndClearDialogs()
            requireContext().showThrowableMessage(it)
        }
    }

    override fun renderView(savedInstanceState: Bundle?) {

    }
}

"""

