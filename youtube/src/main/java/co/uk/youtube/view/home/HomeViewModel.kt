package co.uk.youtube.view.home

import androidx.lifecycle.ViewModel
import co.uk.youtube.api.YoutubeApiService
import co.uk.youtube.error.ExceptionTransformers
import co.uk.youtube.util.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService)
    : ViewModel()