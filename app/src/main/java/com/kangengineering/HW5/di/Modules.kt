package com.kangengineering.HW5.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kangengineering.HW5.data.CatDatabase
import com.kangengineering.HW5.data.CatsAPI
import com.kangengineering.HW5.data.LocationDataDatabase
import com.kangengineering.HW5.data.MapRepository
import com.kangengineering.HW5.data.MapRepositoryImpl
import com.kangengineering.HW5.data.PetsRepository
import com.kangengineering.HW5.data.PetsRepositoryImpl
import com.kangengineering.HW5.viewmodel.MapViewModel
import com.kangengineering.HW5.viewmodel.PetsViewModel
import com.kangengineering.HW5.workers.LocationDataSyncWorker
import com.kangengineering.HW5.workers.PetsSyncWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit
@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
}

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl(get(), get(), get()) }
    single<MapRepository> { MapRepositoryImpl(get(), get()) }
    single { Dispatchers.IO }
    single { PetsViewModel(get()) }
    single { MapViewModel(get()) }
    single {
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("https://cataas.com/api/")
            .build()
    }
    single { get<Retrofit>().create(CatsAPI::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            CatDatabase::class.java,
            "cat-database"
        ).build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            LocationDataDatabase::class.java,
            "locationdata-database"
        ).build()
    }
    single { get<CatDatabase>().carDao() }
    single { get<LocationDataDatabase>().locationDataDao() }
    worker { PetsSyncWorker(get(), get(), get()) }
    worker { LocationDataSyncWorker(get(), get(), get()) }
}