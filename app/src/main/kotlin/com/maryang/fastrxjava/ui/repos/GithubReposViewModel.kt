package com.maryang.fastrxjava.ui.repos

import com.maryang.fastrxjava.base.BaseViewModel
import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class GithubReposViewModel(
    private val repository: GithubRepository =
        GithubRepository()
) : BaseViewModel() {

    private val searchSubject = BehaviorSubject.createDefault("" to false)
    val loadingState = PublishSubject.create<Boolean>()
    val reposState = PublishSubject.create<List<GithubRepo>>()

    fun onCreate() {
        compositeDisposable += searchSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.first.isEmpty()) return@subscribe
                loadingState.onNext(it.second)
                repository.searchGithubRepos(it.first) {
                    loadingState.onNext(false)
                    reposState.onNext(it)
                }
            }
    }

    fun searchGithubRepos(search: String) {
        searchSubject.onNext(search to true)
    }

    fun searchGithubRepos() {
        searchSubject.onNext(searchSubject.value!!.first to false)
    }
}
