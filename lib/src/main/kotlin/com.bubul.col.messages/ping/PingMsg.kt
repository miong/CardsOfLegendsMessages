package com.bubul.col.game.core.net.mqtt.messages

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PingMsgPayload(val source : String, val target : String,  val time : Long)

class PingMsg(val source : String, val target : String, val time : Long) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(PingMsgPayload(source,target, time))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/PingService/Ping"

        fun deserialize(data : ByteArray) : PingMsg {
            val payload = Json.decodeFromString<PingMsgPayload>(String(data))
            return PingMsg(payload.source,payload.target, payload.time)
        }
    }
}