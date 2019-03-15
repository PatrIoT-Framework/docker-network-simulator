/*
 * Copyright 2019 Patriot project
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

package io.patriot_framework.network_simulator.docker.container;

import io.patriot_framework.network.simulator.api.model.network.Network;

import java.util.List;

/**
 * The interface Container.
 */
public interface Container {
    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets ip address.
     *
     * @param network the network
     * @return the ip address
     */
    String getIpAddress(Network network);

    /**
     * Gets id.
     *
     * @return the id
     */
    String getId();

    /**
     * Exists boolea..
     *
     * @return the boolean
     */
    boolean exists();

    /**
     * Connect to network.
     *
     * @param networks the networks
     */
    void connectToNetwork(List<Network> networks);

    /**
     * Destroy container.
     */
    void destroyContainer();

}
