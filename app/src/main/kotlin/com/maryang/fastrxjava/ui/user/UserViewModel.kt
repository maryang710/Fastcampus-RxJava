package com.maryang.fastrxjava.ui.user

import android.annotation.SuppressLint
import com.maryang.fastrxjava.base.BaseViewModel
import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class UserViewModel(
    private val repository: GithubRepository = GithubRepository()
) : BaseViewModel() {

    val userState = PublishSubject.create<User>()
    val followersCountState = BehaviorSubject.createDefault(0)
    val reposCountState = BehaviorSubject.createDefault(0)

    fun onCreate(user: User) {
        userState.onNext(user)
        getUserCounts(user)
    }

    @SuppressLint("CheckResult")
    private fun getUserCounts(user: User) {
        repository.getFollowers(user.followersUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { followersCountState.onNext(it.size) },
                { it.printStackTrace() }
            )

        repository.getRepos(user.reposUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { reposCountState.onNext(it.size) },
                { it.printStackTrace() }
            )
    }
}
