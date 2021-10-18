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

package io.patriot_framework.network_simulator.docker.api.iptables.rules;

import java.util.LinkedHashMap;

/**
 * The type Filter rule.
 */
public class FilterRule {
    private LinkedHashMap<String, String> attributes = new LinkedHashMap<>();

    /**
     * Instantiates a new Filter rule with default attributes.
     */
    public FilterRule() {

        attributes.put("action", null);
        attributes.put("chain", null);
        attributes.put("proto", null);
        attributes.put("iface-in", "any");
        attributes.put("iface-out", "any");
        attributes.put("source", null);
        attributes.put("destination", null);
    }

    public String toAPIFormat() {
        String rules= "/rules/";
        for (String value : attributes.values()) {
            rules += value + "/";
        }
        return rules;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public LinkedHashMap<String, String> getAttributes() {
        return attributes;
    }
}
