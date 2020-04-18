package com.maryang.fastrxjava.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.maryang.fastrxjava.base.BaseViewModelActivity
import com.maryang.fastrxjava.entity.User
import kotlinx.android.synthetic.main.activity_user.*


class UserActivity : BaseViewModelActivity() {

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

    override val viewModel: UserViewModel by lazy {
        UserViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.maryang.fastrxjava.R.layout.activity_user)
        val user = intent.getParcelableExtra<User>(KEY_USER)!!
        supportActionBar?.run {
            title = user.userName
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel.userState.subscribe(::showUser)
        viewModel.followersCountState.subscribe {
            followersCount.text = it.toString()
        }
        viewModel.reposCountState.subscribe {
            reposCount.text = it.toString()
        }

        viewModel.onCreate(user)
    }

    private fun showUser(user: User) {
        Glide.with(this)
            .load(user.avatarUrl)
            .into(ownerImage)
        ownerName.text = user.userName
    }
}
