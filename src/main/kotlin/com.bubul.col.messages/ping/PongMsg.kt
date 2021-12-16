package com.bubul.col.messages.ping


import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PongMsgPayload(val sourceEntity : String, val targetEntity : String, val time : Long)

class PongMsg(val sourceEntity : String, val targetEntity : String, val time : Long) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(PongMsgPayload(sourceEntity, targetEntity, time))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/PingService/Pong"
        fun deserialize(data : ByteArray) : PongMsg {
            val payload = Json.decodeFromString<PongMsgPayload>(String(data))
            return PongMsg(payload.sourceEntity, payload.targetEntity, payload.time)
        }
    }
}