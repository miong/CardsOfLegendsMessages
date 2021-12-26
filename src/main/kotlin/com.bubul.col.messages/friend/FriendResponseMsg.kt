package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.swing.text.html.parser.Entity

@Serializable
data class FriendResponseMsgPayload(val source : String, val target : String, val accepted : Boolean, val sourceEntity: String) : MqttMessagePayload(System.currentTimeMillis())

class FriendResponseMsg(val source : String, val target : String, val accepted : Boolean, val sourceEntity: String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendResponseMsgPayload(source,target,accepted,sourceEntity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendResponse"

        fun deserialize(data : ByteArray) : FriendResponseMsg {
            val payload = Json.decodeFromString<FriendResponseMsgPayload>(String(data))
            return FriendResponseMsg(payload.source,payload.target,payload.accepted,payload.sourceEntity)
        }
    }
}