package com.bubul.col.messages.login

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LoginMsgPayload(val sourceEntity : String, val login : String, val mdpSalt : String)

class LoginMsg(val sourceEntity : String, val login : String, val mdpSalt : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginMsgPayload(sourceEntity,login, mdpSalt))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/Login"

        fun deserialize(data : ByteArray) : LoginMsg {
            val payload = Json.decodeFromString<LoginMsgPayload>(String(data))
            return LoginMsg(payload.sourceEntity,payload.login, payload.mdpSalt)
        }
    }
}

class RegisterMsg(val sourceEntity : String, val login : String, val mdpSalt : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginMsgPayload(sourceEntity,login, mdpSalt))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/Register"

        fun deserialize(data : ByteArray) : RegisterMsg {
            val payload = Json.decodeFromString<LoginMsgPayload>(String(data))
            return RegisterMsg(payload.sourceEntity,payload.login, payload.mdpSalt)
        }
    }
}