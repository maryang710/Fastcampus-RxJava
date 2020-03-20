package com.maryang.fastrxjava.ui.issue

import com.maryang.fastrxjava.base.BaseViewModel
import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo

class IssueCreateViewModel(
    private val repository: GithubRepository = GithubRepository()
) : BaseViewModel() {

    lateinit var repo: GithubRepo

    fun onCreate(repo: GithubRepo) {
        this.repo = repo
    }

    fun create(title: String, body: String) =
        repository.createIssue(repo.owner.userName, repo.name, title, body)
}
