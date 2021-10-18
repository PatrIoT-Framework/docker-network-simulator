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

package io.patriot_framework.network_simulator.docker.model.devices.router;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Router' s network interface.
 *
 * Is used for representation of physical interface of router (Container / VM).
 * Contains only most necessary informations about physical interface.
 *
 */
public class NetworkInterface {
    /**
     * The Name.
     */
    @JsonProperty("Name")
    private String name;
    /**
     * The Ip.
     */
    @JsonProperty("IPAddress")
    private String ip;
    /**
     * The Mask.
     */
    private Integer mask;

    public NetworkInterface(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public NetworkInterface(String ip) {
        this.ip = ip;
    }

    /**
     * Instantiates a new TopologyNetwork interface.
     *
     * @param name the network name
     * @param ip   the ip address
     * @param mask the mask
     */
    public NetworkInterface(String name, String ip, Integer mask) {
        this.name = name;
        this.ip = ip;
        this.mask = mask;
    }

    /**
     * Instantiates a new TopologyNetwork interface.
     */
    public NetworkInterface() {
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
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Gets mask.
     *
     * @return the mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * Sets mask.
     *
     * @param mask the mask
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

}
