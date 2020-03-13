package com.example.rickandmortyapp.core.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

abstract class BindedViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
    protected val hasPosition: Boolean
        get() = adapterPosition != NO_POSITION
}