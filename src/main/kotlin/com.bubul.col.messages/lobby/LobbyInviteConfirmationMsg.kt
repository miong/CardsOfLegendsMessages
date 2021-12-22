package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

enum class LobbyInvitationStatus {
    Cancelled,
    Full,
    Accepted
}

@Serializable
data class LobbyInviteConfirmationMsgPayload(val sourceEntity : String, val targetEntity : String, val status : LobbyInvitationStatus)

class LobbyInviteConfirmationMsg(val sourceEntity : String, val targetEntity : String, val status : LobbyInvitationStatus) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LobbyInviteConfirmationMsgPayload(sourceEntity,targetEntity, status))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return baseTopic
    }

    companion object {
        const val baseTopic = "$topic/LobbyService/Response/"

        fun deserialize(data : ByteArray) : LobbyInviteConfirmationMsg {
            val payload = Json.decodeFromString<LobbyInviteConfirmationMsgPayload>(String(data))
            return LobbyInviteConfirmationMsg(payload.sourceEntity,payload.targetEntity, payload.status)
        }
    }
}