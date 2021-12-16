package com.bubul.col.messages.ping

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PingMsgPayload(val sourceEntity : String, val targetEntity : String,  val time : Long)

class PingMsg(val sourceEntity : String, val targetEntity : String, val time : Long) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(PingMsgPayload(sourceEntity,targetEntity, time))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/PingService/Ping"

        fun deserialize(data : ByteArray) : PingMsg {
            val payload = Json.decodeFromString<PingMsgPayload>(String(data))
            return PingMsg(payload.sourceEntity,payload.targetEntity, payload.time)
        }
    }
}