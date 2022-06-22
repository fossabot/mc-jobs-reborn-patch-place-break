/*
 * JobsReborn extension to patch place-break (Bukkit servers)
 * Copyright (C) 2022 - Loïc DUBOIS-TERMOZ
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package fr.djaytan.minecraft.jobs_reborn_patch_place_break.plugin;

import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.bukkit.BlockBreakListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.bukkit.BlockGrowListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.bukkit.BlockPistonListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.bukkit.BlockPlaceListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.bukkit.BlockSpreadListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.jobs.JobsExpGainListener;
import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener.jobs.JobsPrePaymentListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

/** This class represents a register of listeners. */
public class ListenerRegister {

  private final Plugin plugin;
  private final PluginManager pluginManager;

  private final BlockBreakListener blockBreakListener;
  private final BlockGrowListener blockGrowListener;
  private final BlockPistonListener blockPistonListener;
  private final BlockPlaceListener blockPlaceListener;
  private final BlockSpreadListener blockSpreadListener;
  private final JobsExpGainListener jobsExpGainListener;
  private final JobsPrePaymentListener jobsPrePaymentListener;

  public ListenerRegister(
      @NotNull Plugin plugin,
      @NotNull PluginManager pluginManager,
      @NotNull BlockBreakListener blockBreakListener,
      @NotNull BlockGrowListener blockGrowListener,
      @NotNull BlockPistonListener blockPistonListener,
      @NotNull BlockPlaceListener blockPlaceListener,
      @NotNull BlockSpreadListener blockSpreadListener,
      @NotNull JobsExpGainListener jobsExpGainListener,
      @NotNull JobsPrePaymentListener jobsPrePaymentListener) {
    this.plugin = plugin;
    this.pluginManager = pluginManager;

    this.blockBreakListener = blockBreakListener;
    this.blockGrowListener = blockGrowListener;
    this.blockPistonListener = blockPistonListener;
    this.blockPlaceListener = blockPlaceListener;
    this.blockSpreadListener = blockSpreadListener;
    this.jobsExpGainListener = jobsExpGainListener;
    this.jobsPrePaymentListener = jobsPrePaymentListener;
  }

  public void registerListeners() {
    pluginManager.registerEvents(blockBreakListener, plugin);
    pluginManager.registerEvents(blockGrowListener, plugin);
    pluginManager.registerEvents(blockPistonListener, plugin);
    pluginManager.registerEvents(blockPlaceListener, plugin);
    pluginManager.registerEvents(blockSpreadListener, plugin);
    pluginManager.registerEvents(jobsExpGainListener, plugin);
    pluginManager.registerEvents(jobsPrePaymentListener, plugin);
  }
}
