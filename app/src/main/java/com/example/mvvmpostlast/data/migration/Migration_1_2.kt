package com.example.mvvmpostlast.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val Migration_1_2 = object : Migration(1,2){
    override fun migrate(db: SupportSQLiteDatabase) {
      db.execSQL("ALTER TABLE posts ADD COLUMN author TEXT NOT NULL DEFAULT''")
    }

}