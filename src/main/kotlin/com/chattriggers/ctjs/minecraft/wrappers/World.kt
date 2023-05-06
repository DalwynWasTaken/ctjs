package com.chattriggers.ctjs.minecraft.wrappers

import com.chattriggers.ctjs.minecraft.libs.renderer.Renderer
import gg.essential.universal.UMinecraft
import net.minecraft.client.world.ClientWorld
import net.minecraft.registry.Registries

object World {
    /**
     * Gets Minecraft's WorldClient object
     *
     * @return The Minecraft WorldClient object
     */
    @JvmStatic
    fun getWorld(): ClientWorld? = UMinecraft.getMinecraft().world

    @JvmStatic
    fun isLoaded(): Boolean = getWorld() != null

    // TODO(breaking): Remove Sound methods in favor of the Sound class

    @JvmStatic
    fun isRaining(): Boolean = getWorld()?.isRaining ?: false

    @JvmStatic
    fun getRainingStrength(): Float = getWorld()?.getRainGradient(Renderer.partialTicks) ?: -1f

    @JvmStatic
    fun getTime(): Long = getWorld()?.time ?: -1L

    // TODO: Use enum?
    @JvmStatic
    fun getDifficulty(): String = getWorld()?.difficulty.toString()

    @JvmStatic
    fun getMoonPhase(): Int = getWorld()?.moonPhase ?: -1

    // TODO
    // @JvmStatic
    // fun getSeed(): Long = getWorld()?.seed ?: -1L
    //
    // @JvmStatic
    // fun getType(): String {
    //     //#if MC<=10809
    //     return getWorld()?.worldType?.worldTypeName.toString()
    //     //#else
    //     //$$ return getWorld()?.worldType?.name.toString()
    //     //#endif
    // }

    // TODO: Add these back once the wrappers are added
    // /**
    //  * Gets the [Block] at a location in the world.
    //  *
    //  * @param x the x position
    //  * @param y the y position
    //  * @param z the z position
    //  * @return the [Block] at the location
    //  */
    // @JvmStatic
    // fun getBlockAt(x: Number, y: Number, z: Number) = getBlockAt(BlockPos(x, y, z))
    //
    // /**
    //  * Gets the [Block] at a location in the world.
    //  *
    //  * @param pos The block position
    //  * @return the [Block] at the location
    //  */
    // @JvmStatic
    // fun getBlockAt(pos: BlockPos): Block {
    //     return Block(BlockType(getBlockStateAt(pos).block), pos)
    // }
    //
    // /**
    //  * Gets the [IBlockState] at a location in the world.
    //  *
    //  * @param pos The block position
    //  * @return the [IBlockState] at the location
    //  */
    // @JvmStatic
    // fun getBlockStateAt(pos: BlockPos): IBlockState {
    //     return getWorld()!!.getBlockState(pos.toMCBlock())
    // }
    //
    // /**
    //  * Gets all of the players in the world, and returns their wrapped versions.
    //  *
    //  * @return the players
    //  */
    // @JvmStatic
    // fun getAllPlayers(): List<PlayerMP> = getWorld()?.playerEntities?.map(::PlayerMP) ?: listOf()
    //
    // /**
    //  * Gets a player by their username, must be in the currently loaded chunks!
    //  *
    //  * @param name the username
    //  * @return the player with said username, or null if they don't exist.
    //  */
    // @JvmStatic
    // fun getPlayerByName(name: String): PlayerMP? {
    //     return getWorld()?.getPlayerEntityByName(name)?.let(::PlayerMP)
    // }
    //
    // @JvmStatic
    // fun hasPlayer(name: String): Boolean = getWorld()?.getPlayerEntityByName(name) != null
    //
    // @JvmStatic
    // fun getChunk(x: Int, y: Int, z: Int): Chunk {
    //     return Chunk(
    //         getWorld()!!.getChunkFromBlockCoords(
    //             MCBlockPos(x, y, z)
    //         )
    //     )
    // }
    //
    // @JvmStatic
    // fun getAllEntities(): List<Entity> {
    //     return getWorld()?.loadedEntityList?.map(::Entity) ?: listOf()
    // }
    //
    // /**
    //  * Gets every entity loaded in the world of a certain class
    //  *
    //  * @param clazz the class to filter for (Use `Java.type().class` to get this)
    //  * @return the entity list
    //  */
    // @JvmStatic
    // fun getAllEntitiesOfType(clazz: Class<*>): List<Entity> {
    //     return getAllEntities().filter {
    //         clazz.isInstance(it.entity)
    //     }
    // }
    //
    // @JvmStatic
    // fun getAllTileEntities(): List<TileEntity> {
    //     return getWorld()?.loadedTileEntityList?.map(::TileEntity) ?: listOf()
    // }
    //
    // @JvmStatic
    // fun getAllTileEntitiesOfType(clazz: Class<*>): List<TileEntity> {
    //     return getAllTileEntities().filter {
    //         clazz.isInstance(it.tileEntity)
    //     }
    // }

    /**
     * World border object to get border parameters
     */
    object border {
        /**
         * Gets the border center x location.
         *
         * @return the border center x location
         */
        @JvmStatic
        fun getCenterX(): Double = getWorld()!!.worldBorder.centerX

        /**
         * Gets the border center z location.
         *
         * @return the border center z location
         */
        @JvmStatic
        fun getCenterZ(): Double = getWorld()!!.worldBorder.centerZ

        /**
         * Gets the border size.
         *
         * @return the border size
         */
        @JvmStatic
        fun getSize(): Double = getWorld()!!.worldBorder.size

        /**
         * Gets the border target size.
         *
         * @return the border target size
         */
        @JvmStatic
        fun getTargetSize(): Double = getWorld()!!.worldBorder.sizeLerpTarget

        /**
         * Gets the border time until the target size is met.
         *
         * @return the border time until target
         */
        @JvmStatic
        fun getTimeUntilTarget(): Long = getWorld()!!.worldBorder.sizeLerpTime
    }

    /**
     * World spawn object for getting spawn location.
     */
    object spawn {
        /**
         * Gets the spawn x location.
         *
         * @return the spawn x location.
         */
        @JvmStatic
        fun getX(): Int = getWorld()!!.spawnPos.x

        /**
         * Gets the spawn y location.
         *
         * @return the spawn y location.
         */
        @JvmStatic
        fun getY(): Int = getWorld()!!.spawnPos.y

        /**
         * Gets the spawn z location.
         *
         * @return the spawn z location.
         */
        @JvmStatic
        fun getZ(): Int = getWorld()!!.spawnPos.z
    }

    object particle {
        /**
         * Gets an array of all the different particle names you can pass
         * to [spawnParticle]
         *
         * @return the array of name strings
         */
        @JvmStatic
        fun getParticleNames(): List<String> = Registries.PARTICLE_TYPE.keys.map { it.registry.path }.toList()

        /**
         * Spawns a particle into the world with the given attributes,
         * which can be configured further with the returned [Particle]
         *
         * @param particle the name of the particle to spawn, see [getParticleNames]
         * @param x the x coordinate to spawn the particle at
         * @param y the y coordinate to spawn the particle at
         * @param z the z coordinate to spawn the particle at
         * @param xSpeed the motion the particle should have in the x direction
         * @param ySpeed the motion the particle should have in the y direction
         * @param zSpeed the motion the particle should have in the z direction
         * @return the newly spawned particle for further configuration
         */
        // TODO:
        // @JvmStatic
        // fun spawnParticle(
        //     particle: String,
        //     x: Double,
        //     y: Double,
        //     z: Double,
        //     xSpeed: Double,
        //     ySpeed: Double,
        //     zSpeed: Double,
        // ): Particle {
        //     val particleType = EnumParticleTypes.valueOf(particle)
        //
        //     val fx = Client.getMinecraft().renderGlobal.spawnEntityFX(
        //         particleType.particleID,
        //         particleType.shouldIgnoreRange,
        //         x,
        //         y,
        //         z,
        //         xSpeed,
        //         ySpeed,
        //         zSpeed
        //     )
        //
        //     return Particle(fx)
        // }
        //
        // @JvmStatic
        // fun spawnParticle(particle: MCParticle) {
        //     Client.getMinecraft().effectRenderer.addEffect(particle)
        // }
    }
}