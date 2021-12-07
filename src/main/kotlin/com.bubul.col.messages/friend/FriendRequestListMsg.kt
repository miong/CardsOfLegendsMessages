package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FriendRequestListMsgPayload(val sources : List<String>)

class FriendRequestListMsg(val sources : List<String>) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendRequestListMsgPayload(sources))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendRequestList"

        fun deserialize(data : ByteArray) : FriendRequestListMsg {
            val payload = Json.decodeFromString<FriendRequestListMsgPayload>(String(data))
            return FriendRequestListMsg(payload.sources)
        }
    }
}