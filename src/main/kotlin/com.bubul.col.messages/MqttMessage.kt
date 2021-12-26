package com.bubul.col.messages

import kotlinx.serialization.Serializable

@Serializable
open class MqttMessagePayload(val time : Long)

abstract class MqttMessage() {
    abstract fun serialize() : ByteArray

    open fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "col/game"
    }
}