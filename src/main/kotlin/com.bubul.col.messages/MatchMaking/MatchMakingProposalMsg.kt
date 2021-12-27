package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class MatchMakingProposalMsgPayload(val sourceEntity : String, val targetEntity : String) : MqttMessagePayload(System.currentTimeMillis())

class MatchMakingProposalMsg(val sourceEntity : String, val targetEntity : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(MatchMakingProposalMsgPayload(sourceEntity, targetEntity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/MatchMakingService/Proposal"

        fun deserialize(data : ByteArray) : MatchMakingProposalMsg {
            val payload = Json.decodeFromString<MatchMakingProposalMsgPayload>(String(data))
            return MatchMakingProposalMsg(payload.sourceEntity, payload.targetEntity)
        }
    }
}