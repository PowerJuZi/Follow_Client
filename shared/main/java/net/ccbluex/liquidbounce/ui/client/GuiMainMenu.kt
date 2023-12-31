/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.ui.client

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen

import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.resources.I18n

class GuiMainMenu : WrappedGuiScreen() {
    override fun initGui() {
        val defaultHeight = (representedScreen.height/ 3.5).toInt()
        representedScreen.buttonList.add(classProvider.createGuiButton(1, representedScreen.width / 2 - 50, defaultHeight, 100, 20, I18n.format("Singleplayer")))
        representedScreen.buttonList.add(classProvider.createGuiButton(2, representedScreen.width / 2 - 50, defaultHeight + 24, 100, 20, I18n.format("Multiplayer")))
        representedScreen.buttonList.add(classProvider.createGuiButton(100, representedScreen.width / 2 - 50, defaultHeight + 24*2, 100, 20, "AltManager"))
        representedScreen.buttonList.add(classProvider.createGuiButton(0, representedScreen.width / 2 - 50, defaultHeight + 24*3, 100, 20, I18n.format("Options...")))
        representedScreen.buttonList.add(classProvider.createGuiButton(4, representedScreen.width / 2 - 50, defaultHeight + 24*4, 100, 20, I18n.format("Quit Game")))
        super.initGui()
    }
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        representedScreen.drawBackground(0)
        val sr = ScaledResolution(Minecraft.getMinecraft())
        val defaultHeight = (representedScreen.height/ 3.5).toInt()


        //  RoundedUtil.drawRound(representedScreen.width / 2 - 60F, defaultHeight - 30F, representedScreen.width / 2 - 50f , defaultHeight + 100F,CustomColor.ra.get(), Color(255, 255, 255, 50))
        Fonts.font65.drawCenteredString(LiquidBounce.CLIENT_NAME, sr.scaledWidth / 2F, defaultHeight.toFloat() - 30f, -1,true)
        representedScreen.superDrawScreen(mouseX, mouseY, partialTicks)
    }

    override fun actionPerformed(button: IGuiButton) {
        when (button.id) {
            0 -> mc.displayGuiScreen(classProvider.createGuiOptions(this.representedScreen, mc.gameSettings))
            1 -> mc.displayGuiScreen(classProvider.createGuiSelectWorld(this.representedScreen))
            2 -> mc.displayGuiScreen(classProvider.createGuiMultiplayer(this.representedScreen))
            4 -> mc.shutdown()
            100 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiAltManager(this.representedScreen)))
        }
    }
}