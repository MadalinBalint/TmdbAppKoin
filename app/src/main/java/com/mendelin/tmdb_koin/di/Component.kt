package com.mendelin.tmdb_koin.di

import org.koin.core.module.Module

val appComponents = listOf<Module>(
    moduleTmdb
)

val networkComponents = listOf<Module>(
    moduleNetwork
)