package com.kangengineering.HW5.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kangengineering.HW5.data.MapRepository


class LocationDataSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val  mapRepository: MapRepository
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            mapRepository.fetchRemoteMapData()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}


