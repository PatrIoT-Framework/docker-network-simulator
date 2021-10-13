/*
 * Copyright 2021 Patriot project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.patriot_framework.network_simulator.docker;

import java.util.ArrayList;

/**
 * The type Calculated route list with extended add method to avoid inserting Route to non-existing index.
 *
 * @param <t> the type parameter
 */
public class CalculatedRouteList<t> extends ArrayList<t> {
    @Override
    public void add(int i, t o) {
        int size = super.size();
        if (size > i && super.get(i) == null) {
            super.set(i, o);
        } else {
            if (super.size() <= i) {
                for (int y = super.size(); y < i; y++) {
                    super.add(null);
                }
            }
            super.add(i, o);
        }
    }
}
