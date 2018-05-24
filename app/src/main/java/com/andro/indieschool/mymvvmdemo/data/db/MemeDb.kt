package com.andro.indieschool.mymvvmdemo.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.andro.indieschool.mymvvmdemo.data.db.vo.MemeEntity

@Database(
        entities = [(MemeEntity::class)],
        version = 1,
        exportSchema = false
)
abstract class MemeDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): MemeDb {
            val dbBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, MemeDb::class.java)
            } else {
                Room.databaseBuilder(context, MemeDb::class.java, "meme.db")
            }
            return dbBuilder
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    abstract fun crud(): MemeDao
}