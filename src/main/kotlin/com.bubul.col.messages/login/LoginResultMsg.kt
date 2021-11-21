package com.bubul.col.messages.login

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

enum class LoginResultItem {
    Success,
    Failed
}

@Serializable
data class LoginRestultMsgPayload(val target : String, val login : String, val resultItem : LoginResultItem)

class LoginRestultMsg(val target : String, val login : String, val resultItem : LoginResultItem) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginRestultMsgPayload(target,login, resultItem))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/PingService/LoginResult"

        fun deserialize(data : ByteArray) : LoginRestultMsg {
            val payload = Json.decodeFromString<LoginRestultMsgPayload>(String(data))
            return LoginRestultMsg(payload.target,payload.login, payload.resultItem)
        }
    }
}