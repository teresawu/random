package co.uk.random.view.channel

import co.uk.random.model.Channel
import co.uk.random.model.Item

class ChannelAdapterDelegate() {
    fun onBind(holder: ChannelViewHolder, channel: Item) {
        with(holder) {
            channelTitle.text = channel.kind
        }
    }
}