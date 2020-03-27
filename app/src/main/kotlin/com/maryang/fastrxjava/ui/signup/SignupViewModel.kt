package com.maryang.fastrxjava.ui.signup

import com.maryang.fastrxjava.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject

class SignupViewModel : BaseViewModel() {

    val check1State = BehaviorSubject.createDefault(false)
    val check2State = BehaviorSubject.createDefault(false)
    val check3State = BehaviorSubject.createDefault(false)
    val buttonState = BehaviorSubject.createDefault(false)

    init {
        compositeDisposable += Observable.combineLatest(
            check1State,
            check2State,
            check3State,
            Function3<Boolean, Boolean, Boolean, Boolean> { check1, check2, check3 ->
                check1 && check2 && check3
            }
        ).subscribe(buttonState::onNext)
    }
}
