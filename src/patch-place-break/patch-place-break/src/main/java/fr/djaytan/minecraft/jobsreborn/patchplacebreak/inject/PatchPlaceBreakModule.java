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
package fr.djaytan.minecraft.jobsreborn.patchplacebreak.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.api.PatchPlaceBreakApi;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.impl.PatchPlaceBreakImpl;
import java.nio.file.Path;
import lombok.NonNull;

/** Represents general patch configs. */
public class PatchPlaceBreakModule extends AbstractModule {

  private final ClassLoader classLoader;
  private final Path dataFolder;

  public PatchPlaceBreakModule(@NonNull ClassLoader classLoader, @NonNull Path dataFolder) {
    this.classLoader = classLoader;
    this.dataFolder = dataFolder;
  }

  @Override
  protected void configure() {
    bind(PatchPlaceBreakApi.class).to(PatchPlaceBreakImpl.class);
  }

  @Provides
  @Singleton
  public @NonNull ClassLoader provideClassLoader() {
    return classLoader;
  }

  @Provides
  @Named("dataFolder")
  @Singleton
  public @NonNull Path provideDataFolder() {
    // TODO: create folder if doesn't exists (same to do in bukkit-plugin side if applicable)
    return dataFolder;
  }
}
