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

package io.patriot_framework.network_simulator.docker.api.iptables.chain;

/**
 * IpTables chain.
 */
public class Chain {
    private String name;
    private String table;

    /**
     * Instantiates a new Chain.
     *
     * @param name  the name
     * @param table the table
     */
    public Chain(String name, String table) {
        this.name = name;
        this.table = table;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return "/chain/" + table + "/" + name + "/";
    }
}
