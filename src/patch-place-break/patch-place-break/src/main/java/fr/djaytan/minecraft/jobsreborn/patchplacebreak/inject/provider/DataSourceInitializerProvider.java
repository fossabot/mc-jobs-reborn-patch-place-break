/*
 * MIT License
 *
 * Copyright (c) 2023 Loïc DUBOIS-TERMOZ (alias Djaytan)
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

package fr.djaytan.minecraft.jobsreborn.patchplacebreak.inject.provider;

import fr.djaytan.minecraft.jobsreborn.patchplacebreak.PatchPlaceBreakException;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.api.properties.DataSourceProperties;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.api.properties.DataSourceType;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.mysql.MysqlDataSourceInitializer;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.sql.init.DataSourceInitializer;
import fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.sqlite.SqliteDataSourceInitializer;
import javax.inject.Inject;
import javax.inject.Provider;
import lombok.NonNull;

public class DataSourceInitializerProvider implements Provider<DataSourceInitializer> {

  private final DataSourceProperties dataSourceProperties;
  private final MysqlDataSourceInitializer mysqlDataSourceInitializer;
  private final SqliteDataSourceInitializer sqliteDataSourceInitializer;

  @Inject
  public DataSourceInitializerProvider(
      DataSourceProperties dataSourceProperties,
      MysqlDataSourceInitializer mysqlDataSourceInitializer,
      SqliteDataSourceInitializer sqliteDataSourceInitializer) {
    this.dataSourceProperties = dataSourceProperties;
    this.mysqlDataSourceInitializer = mysqlDataSourceInitializer;
    this.sqliteDataSourceInitializer = sqliteDataSourceInitializer;
  }

  @Override
  public @NonNull DataSourceInitializer get() {
    DataSourceType dataSourceType = dataSourceProperties.getType();

    switch (dataSourceType) {
      case MYSQL:
        {
          return mysqlDataSourceInitializer;
        }
      case SQLITE:
        {
          return sqliteDataSourceInitializer;
        }
      default:
        {
          throw PatchPlaceBreakException.unsupportedDataSourceType(dataSourceType);
        }
    }
  }
}
