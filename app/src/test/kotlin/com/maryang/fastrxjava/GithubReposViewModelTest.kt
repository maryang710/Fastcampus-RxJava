package com.maryang.fastrxjava

import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo
import com.maryang.fastrxjava.ui.repos.GithubReposViewModel
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GithubReposViewModelTest {

    private lateinit var viewModel: GithubReposViewModel

    @Mock
    lateinit var githubRepository: GithubRepository

    private val repos: List<GithubRepo> = emptyList()
    private val searchText = "searchText"

//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
//        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
//        viewModel = GithubReposViewModel(githubRepository)
//
//        Mockito.`when`(githubRepository.searchGithubRepos(Mockito.anyString()))
//            .thenReturn(Single.just(repos))
//        Mockito.`when`(githubRepository.checkStar(Mockito.anyString(), Mockito.anyString()))
//            .thenReturn(Completable.complete())
//    }
//
//    @Test
//    fun searchTest() {
//        viewModel.searchGithubRepos(searchText)
//        viewModel.reposState.test().assertValue(repos)
//    }
}
