package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FriendStatusRequestMsgPayload(val source : String, val entity : String)

class FriendStatusRequestMsg(val source : String, val entity : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendStatusRequestMsgPayload(source, entity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendStatusRequest"

        fun deserialize(data : ByteArray) : FriendStatusRequestMsg {
            val payload = Json.decodeFromString<FriendStatusRequestMsgPayload>(String(data))
            return FriendStatusRequestMsg(payload.source, payload.entity)
        }
    }
}