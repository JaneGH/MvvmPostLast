package com.example.mvvmpostlast.di
import com.example.mvvmpostlast.data.repository.AirplaneRepositoryImpl
import com.example.mvvmpostlast.data.repository.CmsRepositoryImpl
import com.example.mvvmpostlast.data.repository.PostRepositoryImpl
import com.example.mvvmpostlast.data.repository.ThemeRepositoryImpl
import com.example.mvvmpostlast.data.repository.UploadWorkRepositoryImpl
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import com.example.mvvmpostlast.domain.repository.ICmsRepository
import com.example.mvvmpostlast.domain.repository.IPostRepository
import com.example.mvvmpostlast.domain.repository.IThemeRepository
import com.example.mvvmpostlast.domain.repository.IUploadWorkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule(){
    @Binds
    @Singleton
    abstract fun bindPostRepository(
        impl: PostRepositoryImpl
    ) : IPostRepository

    @Binds
    @Singleton
    abstract  fun bindUploadWorkRepository(
        impl: UploadWorkRepositoryImpl
    ): IUploadWorkRepository

    @Binds
    @Singleton
    abstract fun bindCmsRepository(
        impl: CmsRepositoryImpl
    ): ICmsRepository

    @Binds
    @Singleton
    abstract fun bindAirplaneRepository(
        impl: AirplaneRepositoryImpl
    ): IAirplaneRepository

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        impl: ThemeRepositoryImpl
    ): IThemeRepository
}