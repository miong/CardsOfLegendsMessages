package com.bubul.col.messages.login

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class LoginMsgPayload(val source : String, val login : String, val mdpSalt : String)

class LoginMsg(val source : String, val login : String, val mdpSalt : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginMsgPayload(source,login, mdpSalt))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/Login"

        fun deserialize(data : ByteArray) : LoginMsg {
            val payload = Json.decodeFromString<LoginMsgPayload>(String(data))
            return LoginMsg(payload.source,payload.login, payload.mdpSalt)
        }
    }
}

class RegisterMsg(val source : String, val login : String, val mdpSalt : String) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(LoginMsgPayload(source,login, mdpSalt))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/LoginService/Register"

        fun deserialize(data : ByteArray) : LoginMsg {
            val payload = Json.decodeFromString<LoginMsgPayload>(String(data))
            return LoginMsg(payload.source,payload.login, payload.mdpSalt)
        }
    }
}