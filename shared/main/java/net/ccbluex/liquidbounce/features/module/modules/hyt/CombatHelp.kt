package net.ccbluex.liquidbounce.features.module.modules.hyt

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.MoveEvent
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.FloatValue


@ModuleInfo(name = "CancelC03", description = "", category = ModuleCategory.HYT)
class CombatHelp : Module(){
    val isSetRange = BoolValue("SetKillAuraRange",false)
    val rangeValue = FloatValue("Range",5F,0F,7F)

    val killAura = LiquidBounce.moduleManager[KillAura::class.java] as KillAura

    var range = 0F
    var ticks = 0

    var airbypass = false

    override fun onEnable() {
        range = 0F
        airbypass = false
        range = killAura.rangeValue.get()
    }

    override fun onDisable() {
        killAura.rangeValue.set(range)
        range = 0F
        airbypass = false
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent){
        if (isSetRange.get() && killAura.rangeValue.get() != rangeValue.get()) killAura.rangeValue.set(rangeValue.get())
    }
    @EventTarget
    fun onPacket(event: PacketEvent){
        val packet = event.packet
        if (classProvider.isCPacketPlayer(packet)){
            event.cancelEvent()
        }
    }
    @EventTarget
    fun onMove(event: MoveEvent){
        ticks = 0
        if(ticks == 0) {
            event.zero()
        }
        if(ticks == 10) {
            event.zeroXZ()
            return
        }
      ticks++
    }
}