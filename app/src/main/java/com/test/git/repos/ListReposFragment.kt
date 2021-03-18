package com.test.git.repos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.ReposResponseModel
import com.test.git.R
import com.test.git.repos.adapter.ReposAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListReposFragment : Fragment(), ReposAdapter.OnSelectedCallback {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipesAdapter = ReposAdapter(viewModel.listRepos)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        recipesAdapter.setCallback(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_repos, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ListReposFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemSelected(item: ReposResponseModel) {
        Navigation.findNavController(
            requireActivity(), R.id.fragment_container
        ).navigate(
            ListReposFragmentDirections.actionListReposFragmentToDetailRepoFragment())
    }
}