package com.pahadi.composenewsapp.database

interface DatabaseService {
    // saving news
    suspend fun upsert()
}