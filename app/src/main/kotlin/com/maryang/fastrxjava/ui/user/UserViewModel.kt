package com.maryang.fastrxjava.ui.user

import com.maryang.fastrxjava.base.BaseViewModel
import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo
import com.maryang.fastrxjava.entity.User
import com.maryang.fastrxjava.observer.DefaultSingleObserver
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

    private fun getUserCounts(user: User) {
        repository.getFollowers(user.followersUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultSingleObserver<List<User>>() {
                override fun onSuccess(t: List<User>) {
                    followersCountState.onNext(t.size)
                }
            })

        repository.getRepos(user.reposUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DefaultSingleObserver<List<GithubRepo>>() {
                override fun onSuccess(t: List<GithubRepo>) {
                    reposCountState.onNext(t.size)
                }
            })
    }
}
