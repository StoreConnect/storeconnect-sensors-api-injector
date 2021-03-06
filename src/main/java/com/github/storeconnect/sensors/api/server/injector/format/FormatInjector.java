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
package com.github.storeconnect.sensors.api.server.injector.format;

import de.fraunhofer.iosb.ilt.sta.ServiceFailureException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Base type for any {@link Format} injector
 *
 * @param <T> the specific {@link Format} this injector can handle
 * @author Aurelien Bourdon
 */
public interface FormatInjector<T extends Format> extends Runnable {

    void initEnvironment() throws ServiceFailureException;

    List<T> parse(File input) throws IOException;

    void inject(List<T> data) throws ServiceFailureException;

}
