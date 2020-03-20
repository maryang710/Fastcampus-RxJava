package com.maryang.fastrxjava.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.maryang.fastrxjava.base.BaseActivity
import com.maryang.fastrxjava.entity.User


class UserActivity : BaseActivity() {

    companion object {
        private const val KEY_USER = "KEY_USER"

        fun start(context: Context, user: User) {
            context.run {
                startActivity(
                    Intent(this, UserActivity::class.java)
                        .putExtra(KEY_USER, user)
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.maryang.fastrxjava.R.layout.activity_user)
        intent.getParcelableExtra<User>(KEY_USER).let {
            supportActionBar?.run {
                title = it.userName
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }
}
