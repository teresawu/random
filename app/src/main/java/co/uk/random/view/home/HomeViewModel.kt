package co.uk.random.view.home

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Channel
import co.uk.random.model.Playlist
import co.uk.random.util.RealmHelper
import co.uk.random.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class HomeViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {

}