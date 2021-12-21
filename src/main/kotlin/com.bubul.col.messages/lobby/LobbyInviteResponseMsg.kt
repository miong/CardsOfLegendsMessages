package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LobbyInviteResponseMsgPayload(val sourceEntity : String, val targetEntity : String, val accepted : Boolean)

class LobbyInviteResponseMsg(val sourceEntity : String, val targetEntity : String, val accepted : Boolean) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LobbyInviteResponseMsgPayload(sourceEntity,targetEntity, accepted))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return baseTopic
    }

    companion object {
        const val baseTopic = "$topic/LobbyService/Response/"

        fun deserialize(data : ByteArray) : LobbyInviteResponseMsg {
            val payload = Json.decodeFromString<LobbyInviteResponseMsgPayload>(String(data))
            return LobbyInviteResponseMsg(payload.sourceEntity,payload.targetEntity, payload.accepted)
        }
    }
}