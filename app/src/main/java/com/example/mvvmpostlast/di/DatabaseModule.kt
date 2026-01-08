package com.example.mvvmpostlast.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmpostlast.data.local.AppDatabase
import com.example.mvvmpostlast.data.local.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "post_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao{
        return db.postDao()
    }
}