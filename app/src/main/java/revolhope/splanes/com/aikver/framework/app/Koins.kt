package revolhope.splanes.com.aikver.framework.app

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import revolhope.splanes.com.aikver.framework.datasourceimpl.ApiClientFactory
import revolhope.splanes.com.aikver.framework.datasourceimpl.FirebaseDataSourceImpl
import revolhope.splanes.com.aikver.framework.datasourceimpl.SharedPreferencesDataSourceImpl
import revolhope.splanes.com.aikver.framework.helper.ImageLoaderHelper
import revolhope.splanes.com.aikver.presentation.feature.menu.profile.ProfileViewModel
import revolhope.splanes.com.aikver.presentation.feature.menu.profile.avatar.UserAvatarViewModel
import revolhope.splanes.com.aikver.presentation.feature.onboarding.login.LoginViewModel
import revolhope.splanes.com.aikver.presentation.feature.onboarding.register.RegisterViewModel
import revolhope.splanes.com.aikver.presentation.feature.onboarding.splash.SplashViewModel
import revolhope.splanes.com.core.data.datasource.ApiDataSource
import revolhope.splanes.com.core.data.datasource.FirebaseDataSource
import revolhope.splanes.com.core.data.datasource.SharedPreferencesDataSource
import revolhope.splanes.com.core.data.repository.GroupRepository
import revolhope.splanes.com.core.data.repository.UserRepository
import revolhope.splanes.com.core.domain.repositoryimpl.GroupRepositoryImpl
import revolhope.splanes.com.core.domain.repositoryimpl.UserRepositoryImpl
import revolhope.splanes.com.core.interactor.group.FetchUserGroupsUseCase
import revolhope.splanes.com.core.interactor.user.DoLoginUseCase
import revolhope.splanes.com.core.interactor.user.avatar.FetchUserAvatarTypesUseCase
import revolhope.splanes.com.core.interactor.user.FetchUserByNameUseCase
import revolhope.splanes.com.core.interactor.user.FetchUserLoginUseCase
import revolhope.splanes.com.core.interactor.user.FetchUserUseCase
import revolhope.splanes.com.core.interactor.user.InsertUserLoginUseCase
import revolhope.splanes.com.core.interactor.user.RegisterUserUseCase
import revolhope.splanes.com.core.interactor.user.avatar.InsertUserAvatarUseCase

/* Repositories */
val repositoryModule = module(override = true) {
    factory<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    factory<GroupRepository> { GroupRepositoryImpl(get()) }
}

/* DataSources */
val dataSourceModule = module(override = true) {
    factory<FirebaseDataSource> { FirebaseDataSourceImpl() }
    factory<SharedPreferencesDataSource> { SharedPreferencesDataSourceImpl(get()) }
    factory { ApiClientFactory.create(ApiDataSource::class.java) }
}

/* UseCases */
val useCaseModule = module(override = true) {

    /* User use cases */
    factory { InsertUserLoginUseCase(get()) }
    factory { FetchUserLoginUseCase(get()) }
    factory { FetchUserUseCase(get()) }
    factory { FetchUserByNameUseCase(get()) }
    factory { FetchUserAvatarTypesUseCase(get()) }
    factory { InsertUserAvatarUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
    factory { DoLoginUseCase(get()) }

    /* Group use cases */
    factory { FetchUserGroupsUseCase(get()) }

    /* Series use cases */
    /*factory { AddSerieUseCase(get()) }
    factory { GetSeriesUseCase(get()) }
    factory { GetRecentSeriesUseCase(get()) }
    factory { UpdateSerieUseCase(get()) }
    factory { DeleteSerieUseCase(get()) }*/
}

/* Helpers */
val helperModule = module(override = true) {
    factory { ImageLoaderHelper(get()) }
}

val viewModelModule = module(override = true) {
    viewModel { SplashViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { UserAvatarViewModel(get(), get()) }
    //viewModel { DetailsSerieViewModel(get(), get()) }
}