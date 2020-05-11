package com.example.rickandmortyapp.core.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.LoadingView


class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
            .setView(R.layout.dialog_loading)
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCancelable = false
    }


    companion object {

        fun view(fm: FragmentManager, tag: String): LoadingView {
            return object : LoadingView {

                private var dialog: LoadingDialog? = null

                override fun showProgress() {
                    if (dialog != null) return
                    dialog = LoadingDialog().also {
                        fm.beginTransaction()
                            .add(it, tag)
                            .commitAllowingStateLoss()
                    }
                }

                override fun hideProgress() {
                    val dialog = (fm.findFragmentByTag(tag) as LoadingDialog?) ?: dialog
                    dialog?.dismissAllowingStateLoss()
                    this.dialog = null
                }
            }
        }

        fun view(fragment: androidx.fragment.app.Fragment, tag: String): LoadingView {
            return view(
                fragment.childFragmentManager,
                tag
            )
        }
    }
}