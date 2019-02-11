package co.uk.youtube.view.channel.state

import co.uk.youtube.model.Channel

data class ChannelState(
        var loading: Boolean = true,
        var loaded: Boolean = false,
        var channel: Channel = Channel(),
        var error: Boolean = false
)
