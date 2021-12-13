package com.bubul.col.messages.login

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LogoutMsgPayload(val source : String, val login : String)

class LogoutMsg(val source : String, val login : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LogoutMsgPayload(source,login))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/Logout"

        fun deserialize(data : ByteArray) : LogoutMsg {
            val payload = Json.decodeFromString<LogoutMsgPayload>(String(data))
            return LogoutMsg(payload.source,payload.login)
        }
    }
}