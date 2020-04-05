package revolhope.splanes.com.aikver.presentation.feature.onboarding.splash

import androidx.lifecycle.MutableLiveData
import revolhope.splanes.com.aikver.framework.app.launchAsync
import revolhope.splanes.com.aikver.presentation.common.base.BaseViewModel
import revolhope.splanes.com.core.domain.model.User
import revolhope.splanes.com.core.domain.model.UserLogin
import revolhope.splanes.com.core.interactor.user.DoLoginUseCase
import revolhope.splanes.com.core.interactor.user.FetchUserLoginUseCase
import revolhope.splanes.com.core.interactor.user.FetchUserUseCase

class SplashViewModel(
    private val fetchUserLoginUseCase: FetchUserLoginUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val doLoginUseCase: DoLoginUseCase
) : BaseViewModel() {

    val userLogin: MutableLiveData<UserLogin?> get() = _userLogin
    private val _userLogin = MutableLiveData<UserLogin?>()

    val userLoginResult: MutableLiveData<Boolean> get() = _userLoginResult
    private val _userLoginResult = MutableLiveData<Boolean>()

    val user: MutableLiveData<User?> get() = _user
    private val _user = MutableLiveData<User?>()

    fun getUserLogin() = launchAsync { _userLogin.postValue(fetchUserLoginUseCase.invoke()) }

    fun getUser() = launchAsync { _user.postValue(fetchUserUseCase.invoke()) }

    fun doLogin(user: UserLogin) =
        launchAsync { _userLoginResult.postValue(doLoginUseCase.invoke(user)) }
}