package com.maryang.fastrxjava.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        if (!super.onSupportNavigateUp())
            onBackPressed()
        return true
    }
}

abstract class BaseViewModelActivity : BaseActivity() {
    abstract val viewModel: BaseViewModel

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
