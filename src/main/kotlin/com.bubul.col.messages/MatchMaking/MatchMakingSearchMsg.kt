package com.bubul.col.messages.lobby

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class MatchMakingSearchMsgPayload(val sourceEntity : String) : MqttMessagePayload(System.currentTimeMillis())

class MatchMakingSearchMsg(val sourceEntity : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(MatchMakingSearchMsgPayload(sourceEntity))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/MatchMakingService/Search/"

        fun deserialize(data : ByteArray) : MatchMakingSearchMsg {
            val payload = Json.decodeFromString<MatchMakingSearchMsgPayload>(String(data))
            return MatchMakingSearchMsg(payload.sourceEntity)
        }
    }
}