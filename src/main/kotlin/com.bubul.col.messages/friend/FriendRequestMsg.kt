package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FriendRequestMsgPayload(val source : String, val target : String)

class FriendRequestMsg(val source : String, val target : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendRequestMsgPayload(source,target))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendRequest"

        fun deserialize(data : ByteArray) : FriendRequestMsg {
            val payload = Json.decodeFromString<FriendRequestMsgPayload>(String(data))
            return FriendRequestMsg(payload.source,payload.target)
        }
    }
}