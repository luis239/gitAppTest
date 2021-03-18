package com.test.git.extensions

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputLayout
import com.test.git.R
import com.test.git.common.LoadingDialog

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    error = errorMessage
    if (!errorMessage.isNullOrEmpty()) {
        requestFocus()
    }
}
fun FragmentActivity.showLoadingDialog(title: String? = null, message: String? = null) {
    LoadingDialog.showLoadingDialog(supportFragmentManager, title, message)
}
fun FragmentActivity.hideLoadingDialog() {
    LoadingDialog.hideLoadingDialog(supportFragmentManager)
}

fun FragmentActivity.showMessage(message: String?,
                                 acceptListener: DialogInterface.OnClickListener? = null,
                                 dismissListener: DialogInterface.OnDismissListener? = null, cancelable:Boolean = true) {
    if (message != null) {
        AlertDialog.Builder(this)
            .setCancelable(cancelable)
            .setIcon(R.drawable.git_logo)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton("Aceptar", acceptListener)
            .setOnDismissListener(dismissListener)
            .create()
            .show()
    }
}