package net.ccbluex.liquidbounce.features.module.modules.hyt
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.injection.backend.unwrap
import net.ccbluex.liquidbounce.value.TextValue
import net.minecraft.network.play.server.SPacketChat

@ModuleInfo(name = "AutoGG", category = ModuleCategory.HYT, description = "DaShaBi")
class AutoGG : Module() {
    var fadeState = FadeState.NO
    private val textValue = TextValue("Text", "[Follow]Good Game")
    var totalPlayed = 0
    var win = 0
    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet.unwrap()

        if (packet is SPacketChat) {
            val text = packet.chatComponent.unformattedText

            if (text.contains("恭喜", true)) {
                mc.thePlayer!!.sendChatMessage(textValue.get())
                win++
                fadeState = FadeState.FRIST

            }
            if (text.contains("游戏开始", true)) {
                totalPlayed++
            }
        }
    }
    override val tag: String
        get() = "HuaYuTing"
}
enum class FadeState { FRIST,IN, STAY, OUT, END,NO }
