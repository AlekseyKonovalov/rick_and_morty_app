package com.example.rickandmortyapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindedViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}