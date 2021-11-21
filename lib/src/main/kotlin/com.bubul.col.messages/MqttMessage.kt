package com.bubul.col.messages

abstract class MqttMessage() {
    abstract fun serialize() : ByteArray

    open fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "col/game"
    }
}