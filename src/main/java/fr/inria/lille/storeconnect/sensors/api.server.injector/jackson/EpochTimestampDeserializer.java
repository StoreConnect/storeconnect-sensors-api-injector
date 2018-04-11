/**
 * Copyright 2018 Inria Lille
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.inria.lille.storeconnect.sensors.injector.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;

/**
 * {@link Instant} {@link JsonDeserializer} when value is serialized as milliseconds from epoch
 *
 * @author Aurelien Bourdon
 */
public class EpochTimestampDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final String rawTimestamp = node.asText();
        try {
            return Instant.ofEpochMilli(Long.valueOf(rawTimestamp));
        } catch (final NumberFormatException | DateTimeException e) {
            throw new InvalidFormatException(jsonParser, "Invalid millisecond epoch timestamp", rawTimestamp, Long.class);
        }
    }

}
