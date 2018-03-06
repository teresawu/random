package co.uk.random.view.channel

import co.uk.random.model.Channel

class ChannelAdapterDelegate() {
    fun onBind(holder: ChannelViewHolder, channel: Channel) {
        with(holder) {
            channelTitle.text = channel.name
        }
    }
}