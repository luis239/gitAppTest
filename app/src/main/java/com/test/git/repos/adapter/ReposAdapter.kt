package com.test.git.repos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.ReposResponseModel
import com.test.git.databinding.RepoItemBinding


class ReposAdapter (repos: List<ReposResponseModel>): RecyclerView.Adapter<ReposAdapter.ProfileViewHolder>()/*,Filterable*/{

    private var reposListFiltered: ArrayList<ReposResponseModel> = repos as ArrayList<ReposResponseModel>
    private lateinit var context: Context
    private var callback: OnSelectedCallback? = null


    fun setCallback(callback: OnSelectedCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        context = parent.context
        val binding = RepoItemBinding.inflate(LayoutInflater.from(context))
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reposListFiltered.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(reposListFiltered[position])

    }

    inner class ProfileViewHolder(private val itemBinding: RepoItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun  bindItem(itemMeal : ReposResponseModel) {
            itemBinding.txtItemTitle.text = itemMeal.name
            itemBinding.descriptionText.text = itemMeal.dateUpdated
            itemBinding.cardView.setOnClickListener {
                callback?.onItemSelected(itemMeal)
            }



        }

    }


    interface OnSelectedCallback {
        fun onItemSelected(item: ReposResponseModel)
    }

    /*override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                mealsListFiltered = if (charString.isEmpty()) {
                    meals as ArrayList<ReposResponseModel>
                } else {
                    val filteredList: ArrayList<ReposResponseModel> = ArrayList()
                    for (row in meals) {
                        if (row.strMeal!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mealsListFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                mealsListFiltered = filterResults.values as ArrayList<MealResponseModel>
                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }*/
}







