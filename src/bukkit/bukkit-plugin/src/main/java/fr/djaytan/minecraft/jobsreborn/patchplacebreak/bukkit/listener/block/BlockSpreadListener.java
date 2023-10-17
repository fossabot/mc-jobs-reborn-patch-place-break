/*
 * The MIT License
 * Copyright © 2022 Loïc DUBOIS-TERMOZ (alias Djaytan)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.listener.block;

import fr.djaytan.minecraft.jobsreborn.patchplacebreak.bukkit.adapter.PatchPlaceBreakBukkitAdapterApi;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a {@link BlockSpreadEvent} listener.
 *
 * <p>The purpose of this listener is to removed potentially existing place-and-break tag to spread
 * blocks (e.g. we consider that a player-placed dirt block becoming a grass by spreading event
 * shall result to a successful job action when breaking it).
 */
@Singleton
public class BlockSpreadListener implements Listener {

  private final PatchPlaceBreakBukkitAdapterApi patchPlaceBreakBukkitAdapterApi;

  @Inject
  public BlockSpreadListener(
      @NotNull PatchPlaceBreakBukkitAdapterApi patchPlaceBreakBukkitAdapterApi) {
    this.patchPlaceBreakBukkitAdapterApi = patchPlaceBreakBukkitAdapterApi;
  }

  /**
   * This method is called when a {@link BlockSpreadEvent} is dispatched to remove the potentially
   * existing place-and-break patch tag to the spreading block.
   *
   * <p>The EventPriority is set to {@link EventPriority#MONITOR} because we just want to react when
   * we have the confirmation that the event will occur without modifying its result.
   *
   * @param event The block spread event.
   */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onBlockSpread(@NotNull BlockSpreadEvent event) {
    Block block = event.getBlock();
    patchPlaceBreakBukkitAdapterApi.removeTag(block);
  }
}
