/*-
 * #%L
 * JobsReborn-PatchPlaceBreak
 * %%
 * Copyright (C) 2022 - 2023 Loïc DUBOIS-TERMOZ (alias Djaytan)
 * %%
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
 * #L%
 */

package fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.inmemory;

import fr.djaytan.minecraft.jobsreborn.patchplacebreak.api.entities.BlockLocation;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.api.entities.Tag;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.impl.TagRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Singleton;
import lombok.NonNull;

@Singleton
public class TagInMemoryRepository implements TagRepository {

  private final Map<UUID, Tag> tagMap = new HashMap<>();

  @Override
  public void put(@NonNull Tag tag) {
    tagMap.put(tag.getUuid(), tag);
  }

  @Override
  public @NonNull Optional<Tag> findByLocation(@NonNull BlockLocation blockLocation) {
    return tagMap.values().stream()
        .filter(tag -> tag.getBlockLocation().equals(blockLocation))
        .findFirst();
  }

  @Override
  public void delete(@NonNull BlockLocation blockLocation) {
    Optional<Tag> tag = findByLocation(blockLocation);
    tag.ifPresent(t -> tagMap.remove(t.getUuid()));
  }
}
