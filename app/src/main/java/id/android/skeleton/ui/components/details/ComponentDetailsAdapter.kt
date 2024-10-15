package id.android.skeleton.ui.components.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.android.skeleton.common.Utils.setVisibleNotNull
import id.android.skeleton.databinding.ItemDetailBinding

class ComponentDetailsAdapter : ListAdapter<Detail, ComponentDetailsAdapter.DetailViewHolder>(DiffUtilCallback()) {

    private class DiffUtilCallback: DiffUtil.ItemCallback<Detail>() {
        override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class DetailViewHolder(
        private val binding: ItemDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Detail) = with(binding) {
            tvLabel.text = item.label
            tvValue1.text = item.value1.takeIf { !it.isNullOrEmpty() } ?: "-"

            clOptionalField.isVisible = item.optionalFieldCondition
            tvValue2.setVisibleNotNull(item.value2)
            tvValue3.setVisibleNotNull(item.value3)
            tvValue4.setVisibleNotNull(item.value4)

            clOptionalAltField.isVisible = item.optionalAltFiedlCondition
            tvAltValue1.setVisibleNotNull(item.altValue1)
            tvAltValue2.setVisibleNotNull(item.altValue2)
            tvAltValue3.setVisibleNotNull(item.altValue3)
            tvAltValue4.setVisibleNotNull(item.altValue4)
        }
    }
}