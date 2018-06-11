package co.uk.arch.view.main

import android.arch.lifecycle.ViewModel
import co.uk.arch.api.ArchApiService
import co.uk.arch.error.ExceptionTransformers
import co.uk.arch.util.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: ArchApiService)
    : ViewModel()