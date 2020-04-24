package com.maryang.fastrxjava

import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo
import com.maryang.fastrxjava.ui.repos.GithubReposViewModel
import com.maryang.fastrxjava.util.provider.TestSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GithubReposViewModelTest {

    private lateinit var viewModel: GithubReposViewModel

    @Mock
    lateinit var githubRepository: GithubRepository

    private val testSchedulerProvider = TestSchedulerProvider()
    private val repos: List<GithubRepo> = listOf(GithubRepo())
    private val searchText = "searchText"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        Mockito.`when`(githubRepository.searchGithubRepos(Mockito.anyString()))
            .thenReturn(Single.just(repos))
        Mockito.`when`(githubRepository.checkStar(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Completable.complete())

        viewModel = GithubReposViewModel(githubRepository, testSchedulerProvider)
        viewModel.onCreate()
    }

    @Test
    fun searchTest() {
        val observer = viewModel.reposState.test()
        viewModel.searchGithubRepos(searchText)
        testSchedulerProvider.testScheduler.advanceTimeBy(500, TimeUnit.MILLISECONDS)
        observer.assertValue(repos)
    }
}
