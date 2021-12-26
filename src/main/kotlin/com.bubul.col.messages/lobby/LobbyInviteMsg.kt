package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LobbyInviteMsgPayload(val sourceEntity : String, val targetEntity : String) : MqttMessagePayload(System.currentTimeMillis())

class LobbyInviteMsg(val sourceEntity : String, val targetEntity : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LobbyInviteMsgPayload(sourceEntity,targetEntity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return baseTopic
    }

    companion object {
        const val baseTopic = "$topic/LobbyService/Invite/"

        fun deserialize(data : ByteArray) : LobbyInviteMsg {
            val payload = Json.decodeFromString<LobbyInviteMsgPayload>(String(data))
            return LobbyInviteMsg(payload.sourceEntity,payload.targetEntity)
        }
    }
}