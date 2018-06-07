package co.uk.room.view.home

import android.arch.lifecycle.ViewModel
import co.uk.room.api.RoomApiService
import co.uk.room.error.ExceptionTransformers
import co.uk.room.util.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: RoomApiService)
    : ViewModel()