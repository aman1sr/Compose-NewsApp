package com.pahadi.composenewsapp.Util


object Const {

    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val DEFAULT_QUERY_PAGE_SIZE = 20
    const val DEFAULT_PAGE_NUM = 1
    const val DEFAULT_COUNTRY = "in"
    const val DEFAULT_LANGUAGE = "en"
    const val DEFAULT_SOURCE = "abc-news"
//    const val API_KEY = BuildConfig.API_KEY       // todo: add API key
    const val BASE_URL = "https://newsapi.org/v2/"
    const val DB_NAME = "article_db"

    //WorkManager and Notification
    const val UNIQUE_WORK_NAME = "newsAppPeriodicWork"
    const val MORNING_UPDATE_TIME = 5
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "news_channel"
    const val NOTIFICATION_CHANNEL_NAME = "News"
    const val NOTIFICATION_CONTENT_TITLE = "News"
    const val NOTIFICATION_CONTENT_TEXT = "Check out the latest news ..."


}