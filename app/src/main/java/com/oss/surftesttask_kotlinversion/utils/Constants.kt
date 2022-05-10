package com.oss.surftesttask_kotlinversion.utils

import com.oss.surftesttask_kotlinversion.R

object Constants {
    const val API_KEY = "50bd34c2f45cba21762125b1c6069573"
    const val BASE_URL = "https://api.themoviedb.org"
    const val IMG_URL = "https://image.tmdb.org/t/p/w500"
    const val API_VERSION = "3"
    const val WITH_WATCH_MONETIZATION_TYPES = "flatrate"
    const val TV_EMPTY_SEARCH1 = "По запросу "
    const val TV_EMPTY_SEARCH2 = "\n ничего не найдено"
    const val DEFAULT_PICTURE = R.drawable.ic_default_poster

    // REPOSITORY DEFAULT VALUES
    const val DEFAULT_LANGUAGE = "en-US"
    const val DEFAULT_SORT_BY = "popularity.desc"
    const val DEFAULT_INCLUDE_ADULT = false
    const val DEFAULT_INCLUDE_VIDEO = false

    // ROOM DEFAULT VALUES
    const val DEFAULT_LIKED = false
    const val DB_VERSION = 1
    const val DB_NAME = "result_db"
}