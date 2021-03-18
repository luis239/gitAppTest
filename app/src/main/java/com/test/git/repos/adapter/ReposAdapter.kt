package com.test.git.repos.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.ReposResponseModel
import com.test.git.R
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
        return ProfileViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.repo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return reposListFiltered.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.titleText.text = reposListFiltered[position].name
        holder.dateText.text = context.getString(R.string.last_updated,reposListFiltered[position].dateUpdated)
        holder.card.setOnClickListener {
            callback?.onItemSelected(reposListFiltered[position])
        }

    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.txtItemTitle)
        val dateText: TextView = itemView.findViewById(R.id.descriptionText)
        val card: CardView = itemView.findViewById(R.id.cardView)

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







