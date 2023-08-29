package net.ccbluex.liquidbounce.features.module.modules.hyt

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.minecraft.client.Minecraft

@ModuleInfo(name = "HytKillFix", description = "KillFix", category = ModuleCategory.HYT)
class KillFix : Module() {
    private val hurttime: IntegerValue = IntegerValue("hurttime", 9, 1, 10)
    private val hurttime2: IntegerValue = IntegerValue("hurttime2", 10, 1, 10)
    private val AirRange: FloatValue = FloatValue("AirRange", 3f, 0f, 5f)
    private val GroundRange: FloatValue = FloatValue("GroundRange", 3.5f, 0f, 5f)
    private val Debug = BoolValue("Debug", true)
    var ticks = 0

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (Minecraft.getMinecraft().player!!.isAirBorne) {
            val killAura = LiquidBounce.moduleManager[KillAura::class.java] as KillAura
            killAura.rangeValue.set(AirRange.get())
            if (Debug.get()) {
                ClientUtils.displayChatMessage("KillAuraFix -> set range to " + AirRange.value.toString())
            }

        }
        if (Minecraft.getMinecraft().player!!.onGround) {
            val killAura = LiquidBounce.moduleManager[KillAura::class.java] as KillAura
            killAura.rangeValue.set(GroundRange.get())
            if (Debug.get()) {
                ClientUtils.displayChatMessage("KillAuraFix -> set range to " + GroundRange.value.toString())
            }

        }



        }
    }








