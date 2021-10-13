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

package io.patriot_framework.network_simulator.docker.model.routes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.patriot_framework.network_simulator.docker.model.devices.router.NetworkInterface;
import io.patriot_framework.network_simulator.docker.model.devices.router.Router;
import io.patriot_framework.network_simulator.docker.model.network.TopologyNetwork;

/**
 * Used for represent route record in routing tables.
 */
public class Route {

    @JsonIgnore
    private TopologyNetwork source;

    @JsonIgnore
    private Router targetRouter;

    @JsonIgnore
    private Integer mtu = 1400;

    @JsonIgnore
    private Integer hopLimit = 16;

    @JsonProperty("Destination")
    private TopologyNetwork dest;

    @JsonProperty("InterfaceIP")
    private NetworkInterface rNetworkInterface;

    public Route() {
    }

    /**
     * Gets dest.
     *
     * @return the dest
     */
    public TopologyNetwork getDest() {
        return dest;
    }

    /**
     * Sets dest.
     *
     * @param dest the dest
     */
    public void setDest(TopologyNetwork dest) {
        this.dest = dest;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public TopologyNetwork getSource() {
        return source;
    }

    /**
     * Sets source.
     *
     * @param source the source
     */
    public void setSource(TopologyNetwork source) {
        this.source = source;
    }

    /**
     * Gets network interface.
     *
     * @return the network interface
     */
    public NetworkInterface getrNetworkInterface() {
        return rNetworkInterface;
    }

    /**
     * Sets network interface.
     *
     * @param rNetworkInterface the r network interface
     */
    public void setrNetworkInterface(NetworkInterface rNetworkInterface) {
        this.rNetworkInterface = rNetworkInterface;
    }

    /**
     * Gets target router.
     *
     * @return the target router
     */
    public Router getTargetRouter() {
        return targetRouter;
    }

    /**
     * Sets target router.
     *
     * @param targetRouter the target router
     */
    public void setTargetRouter(Router targetRouter) {
        this.targetRouter = targetRouter;
    }

    /**
     * To path string.
     *
     * @return the string
     */
    public String toAPIFormat() {
        return "destination=" + getDest().getIPAddress() + "&mask=" + getDest().getMask() + "&interface=" + rNetworkInterface.getIp();
    }
}
