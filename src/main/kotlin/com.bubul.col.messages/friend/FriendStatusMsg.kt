package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.swing.text.html.parser.Entity

@Serializable
data class FriendStatusMsgPayload(val source : String, val status : FriendStatus, val sourceEntity: String)

class FriendStatusMsg(val source : String, val status : FriendStatus, val sourceEntity: String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendStatusMsgPayload(source,status,sourceEntity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendStatus"

        fun deserialize(data : ByteArray) : FriendStatusMsg {
            val payload = Json.decodeFromString<FriendStatusMsgPayload>(String(data))
            return FriendStatusMsg(payload.source,payload.status,payload.sourceEntity)
        }
    }
}