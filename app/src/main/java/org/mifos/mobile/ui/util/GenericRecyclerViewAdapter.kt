package org.mifos.mobile.ui.util

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


class GenericRecyclerViewAdapter<T: Any, VBType: ViewBinding>(
    items: List<T>,
    itemComparator:(oldItem: T, newItem: T) -> Boolean = { a,b -> a == b},
    private val bindingCreator: (parent: ViewGroup) -> VBType,
    private val bindItemWithHolder: (item: T, binding: VBType) -> Unit = {_,_ ->},
    private val itemOnClick: (item: T) -> Unit = {},
    private val itemOnLongClick: (item: T) -> Boolean = {false}
) : ListAdapter<T, GenericRecyclerViewAdapter.GenericViewHolder>(diffCallBackBy(itemComparator)) {

    init {
        submitList(items)
    }

    class GenericViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val binding = bindingCreator(parent)
        val viewHolder = GenericViewHolder(binding)
        binding.root.setOnClickListener { itemOnClick(getItem(viewHolder.adapterPosition)) }
        binding.root.setOnLongClickListener { itemOnLongClick(getItem(viewHolder.adapterPosition)) }
        return viewHolder
    }


    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(viewHolder: GenericViewHolder, position: Int)
        = bindItemWithHolder(getItem(position) as T, viewHolder.binding as VBType)


}


fun<T> diffCallBackBy(comparator: (oldItem: T, newItem: T) -> Boolean) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T)
            = comparator(oldItem, newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T)
            = (oldItem == newItem)

}