package com.bubul.col.messages.chat

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ChatMsgPayload(val target : String, val content : String)

class ChatMsg(val target : String, val content : String) : MqttMessage() {
    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(ChatMsgPayload(target,content))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return baseTopic
    }

    companion object {
        const val baseTopic = "$topic/ChatService/Chat/"

        fun deserialize(data : ByteArray) : ChatMsg {
            val payload = Json.decodeFromString<ChatMsgPayload>(String(data))
            return ChatMsg(payload.target,payload.content)
        }
    }
}