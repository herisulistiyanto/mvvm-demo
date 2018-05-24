package com.andro.indieschool.mymvvmdemo.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.andro.indieschool.mymvvmdemo.data.db.vo.MemeEntity

@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meme: List<MemeEntity>)

}