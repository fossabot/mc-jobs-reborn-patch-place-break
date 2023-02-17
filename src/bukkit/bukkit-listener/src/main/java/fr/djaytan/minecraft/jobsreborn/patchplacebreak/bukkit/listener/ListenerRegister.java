/*
 * MIT License
 *
 * Copyright (c) 2022-2023 Loïc DUBOIS-TERMOZ (alias Djaytan)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block.BlockBreakListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block.BlockGrowListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block.BlockPistonListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block.BlockPlaceListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block.BlockSpreadListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.jobs.JobsExpGainListener;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.jobs.JobsPrePaymentListener;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/** This class represents a register of listeners. */
@Slf4j
@Singleton
public class ListenerRegister {

  private final JavaPlugin javaPlugin;
  private final PluginManager pluginManager;

  private final BlockBreakListener blockBreakListener;
  private final BlockGrowListener blockGrowListener;
  private final BlockPistonListener blockPistonListener;
  private final BlockPlaceListener blockPlaceListener;
  private final BlockSpreadListener blockSpreadListener;
  private final JobsExpGainListener jobsExpGainListener;
  private final JobsPrePaymentListener jobsPrePaymentListener;

  @Inject
  public ListenerRegister(@NonNull JavaPlugin javaPlugin, @NonNull PluginManager pluginManager,
      @NonNull BlockBreakListener blockBreakListener, @NonNull BlockGrowListener blockGrowListener,
      @NonNull BlockPistonListener blockPistonListener,
      @NonNull BlockPlaceListener blockPlaceListener,
      @NonNull BlockSpreadListener blockSpreadListener,
      @NonNull JobsExpGainListener jobsExpGainListener,
      @NonNull JobsPrePaymentListener jobsPrePaymentListener) {
    this.javaPlugin = javaPlugin;
    this.pluginManager = pluginManager;

    this.blockBreakListener = blockBreakListener;
    this.blockGrowListener = blockGrowListener;
    this.blockPistonListener = blockPistonListener;
    this.blockPlaceListener = blockPlaceListener;
    this.blockSpreadListener = blockSpreadListener;
    this.jobsExpGainListener = jobsExpGainListener;
    this.jobsPrePaymentListener = jobsPrePaymentListener;
  }

  /**
   * The purposes of this method is simple: registering listeners through the {@link PluginManager}.
   */
  public void registerListeners() {
    pluginManager.registerEvents(blockBreakListener, javaPlugin);
    pluginManager.registerEvents(blockGrowListener, javaPlugin);
    pluginManager.registerEvents(blockPistonListener, javaPlugin);
    pluginManager.registerEvents(blockPlaceListener, javaPlugin);
    pluginManager.registerEvents(blockSpreadListener, javaPlugin);
    pluginManager.registerEvents(jobsExpGainListener, javaPlugin);
    pluginManager.registerEvents(jobsPrePaymentListener, javaPlugin);
    log.atInfo().log("Event listeners registered.");
  }

  public void reloadListeners() {
    log.atInfo().log("Reloading event listeners...");
    unregisterListeners();
    registerListeners();
    log.atInfo().log("Event listeners reloaded.");
  }

  private void unregisterListeners() {
    HandlerList.unregisterAll(javaPlugin);
    log.atInfo().log("Event listeners unregistered.");
  }
}
