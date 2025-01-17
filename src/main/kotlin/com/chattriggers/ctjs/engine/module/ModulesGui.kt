package com.chattriggers.ctjs.engine.module

import com.chattriggers.ctjs.minecraft.libs.ChatLib
import com.chattriggers.ctjs.minecraft.libs.renderer.Renderer
import com.chattriggers.ctjs.minecraft.libs.renderer.Text
import com.chattriggers.ctjs.minecraft.wrappers.Player
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack

object ModulesGui : Screen(net.minecraft.text.Text.literal("Modules")) {
    private val window = object {
        var title = Text("Modules").setScale(2f).setShadow(true)
        var exit = Text(ChatLib.addColor("&cx")).setScale(2f)
        var height = 0f
        var scroll = 0f
    }

    override fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(matrices, mouseX, mouseY, delta)

        Renderer.pushMatrix()

        val middle = Renderer.screen.getWidth() / 2f
        var width = Renderer.screen.getWidth() - 100f
        if (width > 500) width = 500f

        Renderer.drawRect(
            0x50000000,
            0f,
            0f,
            Renderer.screen.getWidth().toFloat(),
            Renderer.screen.getHeight().toFloat()
        )

        if (-window.scroll > window.height - Renderer.screen.getHeight() + 20)
            window.scroll = -window.height + Renderer.screen.getHeight() - 20
        if (-window.scroll < 0) window.scroll = 0f

        if (-window.scroll > 0) {
            Renderer.drawRect(0xaa000000.toInt(), Renderer.screen.getWidth() - 20f, Renderer.screen.getHeight() - 20f, 20f, 20f)
            Renderer.drawString("^", Renderer.screen.getWidth() - 12f, Renderer.screen.getHeight() - 12f)
        }

        Renderer.drawRect(0x50000000, middle - width / 2f, window.scroll + 95f, width, window.height - 90)

        Renderer.drawRect(0xaa000000.toInt(), middle - width / 2f, window.scroll + 95f, width, 25f)
        window.title.draw((middle - width / 2f + 5) / 2f, (window.scroll + 100f) / 2f)
        window.exit.setString(ChatLib.addColor("&cx")).draw((middle + width / 2f - 17) / 2f, (window.scroll + 99f) / 2f)

        window.height = 125f
        ModuleManager.cachedModules.forEach {
            window.height += it.draw(middle - width / 2f, window.scroll + window.height, width)
        }

        Renderer.popMatrix()
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        super.mouseClicked(mouseX, mouseY, button)

        var width = Renderer.screen.getWidth() - 100f
        if (width > 500) width = 500f

        if (mouseX > Renderer.screen.getWidth() - 20 && mouseY > Renderer.screen.getHeight() - 20) {
            window.scroll = 0f
            return false
        }

        if (mouseX > Renderer.screen.getWidth() / 2f + width / 2f - 25 && mouseX < Renderer.screen.getWidth() / 2f + width / 2f
            && mouseY > window.scroll + 95 && mouseY < window.scroll + 120
        ) {
            Player.toMC()?.closeScreen()
            return false
        }

        ModuleManager.cachedModules.toList().forEach {
            it.click(mouseX, mouseY, width)
        }

        return true
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean {
        window.scroll += amount.toFloat()

        return super.mouseScrolled(mouseX, mouseY, amount)
    }
}
