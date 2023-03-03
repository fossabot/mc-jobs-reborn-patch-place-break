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
package fr.djaytan.minecraft.jobsreborn.patchplacebreak.storage.api.properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DbmsServerPropertiesTest {

  @Test
  @DisplayName("Constructor - Successful nominal case")
  void shouldSuccessWhenCreatingWithNominalValues() {
    // Given
    DbmsHostProperties hostProperties = DbmsHostProperties.of("host", 80, true);
    CredentialsProperties credentialsProperties = CredentialsProperties.of("username", "password");
    String database = "test_db";

    // When
    DbmsServerProperties dbmsServerProperties =
        DbmsServerProperties.of(hostProperties, credentialsProperties, database);

    // Then
    assertAll(
        "Verification of returned values from getters",
        () -> assertThat(dbmsServerProperties.getHost()).isEqualTo(hostProperties),
        () -> assertThat(dbmsServerProperties.getCredentials()).isEqualTo(credentialsProperties),
        () -> assertThat(dbmsServerProperties.getDatabase()).isEqualTo(database));
  }

  @Test
  @DisplayName("equals() & hashCode() - Verifications")
  void equalsAndHashcodeContractVerification() {
    EqualsVerifier.forClass(DbmsServerProperties.class).verify();
  }

  @Test
  @DisplayName("toString() - Verifications")
  void toStringContractVerification() {
    ToStringVerifier.forClass(DbmsServerProperties.class)
        .withClassName(NameStyle.SIMPLE_NAME)
        .verify();
  }
}
