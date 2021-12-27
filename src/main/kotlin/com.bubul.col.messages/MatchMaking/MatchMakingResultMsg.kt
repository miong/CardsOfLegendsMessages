package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class MatchMakingResultMsgPayload(val sourceEntity : String, val targetEntity : String, val accepted : Boolean) : MqttMessagePayload(System.currentTimeMillis())

class MatchMakingResultMsg(val sourceEntity : String, val targetEntity : String, val accepted : Boolean) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(MatchMakingResultMsgPayload(sourceEntity, targetEntity, accepted))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/MatchMakingService/Result/"

        fun deserialize(data : ByteArray) : MatchMakingResultMsg {
            val payload = Json.decodeFromString<MatchMakingResultMsgPayload>(String(data))
            return MatchMakingResultMsg(payload.sourceEntity, payload.targetEntity, payload.accepted)
        }
    }
}