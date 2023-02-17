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

package fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.adapter.PatchPlaceBreakBukkitAdapter;
import lombok.NonNull;

/**
 * This class represents a {@link BlockBreakEvent} listener.
 *
 * <p>The purpose of this listener is to put a place-and-break patch tag to the newly created {@link
 * org.bukkit.Material#AIR} block when the non-air previous one has been broken to prevent exploits
 * like with saplings or sugarcane.
 */
@Singleton
public class BlockBreakListener implements Listener {

  private final PatchPlaceBreakBukkitAdapter patchPlaceBreakBukkitAdapter;

  @Inject
  public BlockBreakListener(@NonNull PatchPlaceBreakBukkitAdapter patchPlaceBreakBukkitAdapter) {
    this.patchPlaceBreakBukkitAdapter = patchPlaceBreakBukkitAdapter;
  }

  /**
   * This method is called when a {@link BlockBreakEvent} is dispatched to put the place-and-break
   * patch tag.
   *
   * <p>The EventPriority is set to {@link EventPriority#MONITOR} because we just want to react when
   * we have the confirmation that the event will occur without modifying its result. Furthermore,
   * because this plugin will always be enabled before the JobsReborn one (enhance the "depend"
   * plugin.yml line), we have the guarantee that this listener will always be called before the one
   * registered by JobsReborn at the same priority level.
   *
   * @param event The block break event.
   */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onBlockBreak(@NonNull BlockBreakEvent event) {
    Location location = event.getBlock().getLocation();
    patchPlaceBreakBukkitAdapter.putTag(location, true);
  }
}
