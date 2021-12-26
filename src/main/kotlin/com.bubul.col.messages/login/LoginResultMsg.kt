package com.bubul.col.messages.login

import com.bubul.col.messages.MqttMessage
import com.bubul.col.messages.MqttMessagePayload
import kotlinx.serialization.*
import kotlinx.serialization.json.*

enum class LoginResultItem {
    Success,
    Failed
}

@Serializable
data class LoginRestultMsgPayload(val targetEntity : String, val login : String, val resultItem : LoginResultItem) : MqttMessagePayload(System.currentTimeMillis())

open class LoginRestultMsg(val targetEntity : String,val login : String,val resultItem : LoginResultItem) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginRestultMsgPayload(targetEntity,login, resultItem))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/LoginResult"

        fun deserialize(data : ByteArray) : LoginRestultMsg {
            val payload = Json.decodeFromString<LoginRestultMsgPayload>(String(data))
            return LoginRestultMsg(payload.targetEntity,payload.login, payload.resultItem)
        }
    }
}

class RegisterRestulMsg(val targetEntity : String, val login : String, val resultItem : LoginResultItem) : MqttMessage() {
    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginRestultMsgPayload(targetEntity,login, resultItem))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/RegisterResult"

        fun deserialize(data : ByteArray) : RegisterRestulMsg {
            val payload = Json.decodeFromString<LoginRestultMsgPayload>(String(data))
            return RegisterRestulMsg(payload.targetEntity,payload.login, payload.resultItem)
        }
    }
}