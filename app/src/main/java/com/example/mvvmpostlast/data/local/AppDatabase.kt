package com.example.mvvmpostlast.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmpostlast.data.local.dao.PostDao
import com.example.mvvmpostlast.data.local.entity.PostEntity
import com.example.mvvmpostlast.data.migration.Migration_1_2

@Database(version = 1, entities = [PostEntity::class])
abstract class AppDatabase: RoomDatabase(){
    abstract fun postDao(): PostDao
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase{
//            return INSTANCE?:synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "post_database.db"
//                )
//                    .addMigrations(Migration_1_2)
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}
