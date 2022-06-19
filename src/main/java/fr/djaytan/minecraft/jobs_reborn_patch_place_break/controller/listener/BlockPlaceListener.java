/*
 * Blacklisting enchantments plugin for Minecraft (Bukkit servers)
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

package fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.listener;

import fr.djaytan.minecraft.jobs_reborn_patch_place_break.controller.JobsController;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

@Singleton
public class BlockPlaceListener implements Listener {

  private final JobsController jobsController;

  @Inject
  public BlockPlaceListener(@NotNull JobsController jobsController) {
    this.jobsController = jobsController;
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onBlockPlace(@NotNull BlockPlaceEvent event) {
    jobsController.setPlayerBlockPlacedMetadata(event.getBlockPlaced());
  }
}
