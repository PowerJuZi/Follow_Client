package net.ccbluex.liquidbounce.features.module.modules.hyt
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase
import net.ccbluex.liquidbounce.event.AttackEvent
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.TextValue
import net.minecraft.client.Minecraft
import java.util.*

@ModuleInfo(name = "AutoL", category = ModuleCategory.HYT, description = "大傻逼版")
class AutoL : Module() {


    private val L = BoolValue ("L", true)
    var AutoLmsg = arrayOf(
            "@[Nature]人与人的体质是不一样的",
            "@[Nature]q变强裙 久岭叁久岭四岭久②"


    )
    // Target
    var target: IEntityLivingBase? = null

    companion object {
        var kill = 0
        var DeathNum = 0
    }
    @EventTarget
    fun onAttack(event: AttackEvent) {
        target = event.targetEntity as IEntityLivingBase?
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {

        if(Minecraft.getMinecraft().player.isDead){

            DeathNum ++
        }

        if (target!!.health <= 0.1) {
            kill += 1
            if (L.get()) {
                val r = Random()
                mc.thePlayer!!.sendChatMessage(AutoLmsg.get(r.nextInt(AutoLmsg.size)) )
            }
            target = null
        }
    }

    fun kills() : Int {
        return kill
    }
    override val tag: String?
        get() = "Kill $kill"
}
