package com.training.victor.development.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.training.victor.development.R
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_profile_item.view.*

class ProfilesAdapter(private val profilesList: ArrayList<ProfileItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(profilesList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(profileItem: ProfileItem) = with(itemView) {
            Glide.with(itemView.context).load(profileItem.profilePicture).into(imgProfile)
        }
    }
}