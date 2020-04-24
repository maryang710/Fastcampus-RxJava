package com.maryang.fastrxjava.ui.signup

import android.content.Intent
import android.os.Bundle
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.maryang.fastrxjava.R
import com.maryang.fastrxjava.base.BaseViewModelActivity
import com.maryang.fastrxjava.ui.repos.GithubReposActivity
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : BaseViewModelActivity() {

    override val viewModel: SignupViewModel by lazy {
        SignupViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        compositeDisposable += viewModel.buttonState.subscribe {
            signupButton.isEnabled = it
            signupButton.setBackgroundColor(
                if (it) getColor(R.color.colorPrimary)
                else getColor(R.color.grey_500)
            )
        }
        setOnClickListener()
    }

    private fun setOnClickListener() {
        checkbox1.checkedChanges().subscribe(viewModel.check1State)
        checkbox2.checkedChanges().subscribe(viewModel.check2State)
        checkbox3.checkedChanges().subscribe(viewModel.check3State)
        signupButton.setOnClickListener {
            startActivity(
                Intent(this, GithubReposActivity::class.java)
            )
            finish()
        }
    }
}
