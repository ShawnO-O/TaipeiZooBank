package com.zoo.taipeizoo.widget

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zoo.taipeizoo.R

class CustomBottomSheet:BottomSheetDialogFragment() {
    lateinit var behavior :BottomSheetBehavior<View>
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        var view = View.inflate(context, R.layout.dialog_bottom_sheet_plant,null)
        dialog.setContentView(view)
        behavior = BottomSheetBehavior.from(view.parent as View)

        return dialog

    }

    override fun onStart() {
        super.onStart()
    }


}