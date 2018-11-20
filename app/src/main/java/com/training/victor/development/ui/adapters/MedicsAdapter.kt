package com.training.victor.development.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.training.victor.development.R
import com.training.victor.development.data.models.MedicItem
import com.training.victor.development.utils.inflate
import com.training.victor.development.utils.myTrace
import kotlinx.android.synthetic.main.adapter_profile_item.view.*

class MedicsAdapter(private val medicList: List<MedicItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return medicList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(medicList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(medic: MedicItem) = with(itemView) {
            txtName.text = medic.name
            txtAddress.text = medic.address
            myTrace("doctor pgoto :: ${medic.photo}")

        }
    }
}