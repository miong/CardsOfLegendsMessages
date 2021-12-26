package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FriendRemovalMsgPayload(val source : String, val target : String) : MqttMessagePayload(System.currentTimeMillis())

class FriendRemovalMsg(val source : String, val target : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendRemovalMsgPayload(source,target))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendRemoval"

        fun deserialize(data : ByteArray) : FriendRemovalMsg {
            val payload = Json.decodeFromString<FriendRemovalMsgPayload>(String(data))
            return FriendRemovalMsg(payload.source,payload.target)
        }
    }
}