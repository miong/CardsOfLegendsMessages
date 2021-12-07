
package com.bubul.col.messages.friend

import com.bubul.col.messages.MqttMessage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FriendStatusListMsgPayload(val source : String, val friends : Map<String, Boolean>)

class FriendStatusListMsg(val source : String, val friends : Map<String, Boolean>) : MqttMessage() {

    override fun serialize(): ByteArray {
        val payload = Json.encodeToString(FriendStatusListMsgPayload(source, friends))
        return payload.toByteArray()
    }

    override fun getMqttTopic(): String {
        return topic
    }

    companion object {
        const val topic = "${MqttMessage.topic}/FriendService/FriendStatusList"

        fun deserialize(data : ByteArray) : FriendStatusListMsg {
            val payload = Json.decodeFromString<FriendStatusListMsgPayload>(String(data))
            return FriendStatusListMsg(payload.source, payload.friends)
        }
    }
}