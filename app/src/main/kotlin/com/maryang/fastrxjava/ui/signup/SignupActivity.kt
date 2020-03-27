package com.maryang.fastrxjava.ui.signup

import android.content.Intent
import android.os.Bundle
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
        compositeDisposable += viewModel.check1State.subscribe {
            checkbox1.isChecked = it
        }
        compositeDisposable += viewModel.check2State.subscribe {
            checkbox2.isChecked = it
        }
        compositeDisposable += viewModel.check3State.subscribe {
            checkbox3.isChecked = it
        }
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
        checkbox1.setOnClickListener { viewModel.check1State.onNext(checkbox1.isChecked) }
        checkbox2.setOnClickListener { viewModel.check2State.onNext(checkbox2.isChecked) }
        checkbox3.setOnClickListener { viewModel.check3State.onNext(checkbox3.isChecked) }
        signupButton.setOnClickListener {
            startActivity(
                Intent(this, GithubReposActivity::class.java)
            )
        }
    }
}