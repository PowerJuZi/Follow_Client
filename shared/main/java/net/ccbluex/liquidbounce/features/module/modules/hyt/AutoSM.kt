package net.ccbluex.liquidbounce.features.module.modules.hyt
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.injection.backend.unwrap
import net.ccbluex.liquidbounce.value.TextValue
import net.minecraft.network.play.server.SPacketChat
import java.util.*

@ModuleInfo(name = "AntoEmo", category = ModuleCategory.HYT, description = "DaShaBi")
class AutoSM : Module() {

    var EmoMsg = arrayOf(
        "@那句我喜欢你，温暖了我一整个冬季。",
        "@我希望和你并排站在一起，看每个黄昏日落。",
        "@纸短情长，不敢承诺地老天荒。",
        "@别慌，月亮也会在大海的某处迷茫。",
        "@独自走过苍苍莽莽，与你同行才有了光。",
        "@我遇见你，是最灿烂的花开，最美丽的意外。",
        "@他们说，有风的地方。 就是你爱着的人的灵魂在飞舞。"


    )
    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet.unwrap()

        if (packet is SPacketChat) {
            val text = packet.chatComponent.unformattedText

            if (text.contains("游戏开始", true)) {
                val r = Random()
                mc.thePlayer!!.sendChatMessage(EmoMsg.get(r.nextInt(EmoMsg.size)) )

            }
        }
    }
    override val tag: String
        get() = "HuaYuTing"
}

