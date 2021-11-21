package com.bubul.col.messages.ping


import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PongMsgPayload(val source : String, val target : String, val time : Long)

class PongMsg(val source : String, val target : String, val time : Long) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(PongMsgPayload(source, target, time))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/PingService/Pong"
        fun deserialize(data : ByteArray) : PongMsg {
            val payload = Json.decodeFromString<PongMsgPayload>(String(data))
            return PongMsg(payload.source, payload.target, payload.time)
        }
    }
}