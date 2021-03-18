package com.test.git.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.git.BR
import com.test.git.R
import com.test.git.Resource
import com.test.git.databinding.FragmentMainBinding
import com.test.git.extensions.hideLoadingDialog
import com.test.git.extensions.showLoadingDialog
import com.test.git.extensions.showMessage
import com.test.git.repos.adapter.ReposAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    private lateinit var binding: ViewDataBinding
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun searchActionClick(){
        if(viewModel.isValid())
            viewModel.searchAction()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseSearch.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.ResourceState.LOADING -> {
                    activity?.showLoadingDialog()
                }
                Resource.ResourceState.NEXT -> {
                    viewModel.listRepos.addAll(it.data!!.toTypedArray())
                    Navigation.findNavController(
                            requireActivity(), R.id.fragment_container
                        ).navigate(
                            MainFragmentDirections.actionMainFragmentToListReposFragment())
                    activity?.hideLoadingDialog()


                }
                Resource.ResourceState.ERROR -> {
                    activity?.hideLoadingDialog()
                    activity?.showMessage(it.message)
                }
                Resource.ResourceState.COMPLETED -> {
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.setVariable(BR.vm, viewModel)
        binding.setVariable(BR.fragment, this)
        //binding.lifecycleOwner = this
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}