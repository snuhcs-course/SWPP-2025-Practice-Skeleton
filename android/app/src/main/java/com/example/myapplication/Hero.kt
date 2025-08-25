
package com.example.heroes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class Hero(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val age: Int,
    val score: Int
)