package com.maryang.fastrxjava.ui.signup

import android.content.Intent
import android.os.Bundle
import com.maryang.fastrxjava.R
import com.maryang.fastrxjava.base.BaseActivity
import com.maryang.fastrxjava.ui.repos.GithubReposActivity
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : BaseActivity() {

    private var check1Checked = false
    private var check2Checked = false
    private var check3Checked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        checkbox1.setOnCheckedChangeListener { button, checked ->
            check1Checked = checked
            enableSignupButton()
        }
        checkbox2.setOnCheckedChangeListener { button, checked ->
            check2Checked = checked
            enableSignupButton()
        }
        checkbox3.setOnCheckedChangeListener { button, checked ->
            check3Checked = checked
            enableSignupButton()
        }
        signupButton.setOnClickListener {
            startActivity(
                Intent(this, GithubReposActivity::class.java)
            )
            finish()
        }
    }

    private fun enableSignupButton() {
        signupButton.isEnabled = check1Checked && check2Checked && check3Checked
        signupButton.setBackgroundColor(
            if (signupButton.isEnabled) getColor(R.color.colorPrimary)
            else getColor(R.color.grey_500)
        )
    }
}
