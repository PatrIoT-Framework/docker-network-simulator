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

package io.patriot_framework.network_simulator.docker.model;

import io.patriot_framework.network.simulator.api.model.devices.Device;
import io.patriot_framework.network_simulator.docker.model.devices.router.Router;
import io.patriot_framework.network_simulator.docker.model.network.ContainerNetwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Wrapper representing full network topology.
 */
public class Topology {
    /**
     * Routers located in topology.
     */
    private List<Router> routers = new ArrayList<>();

    /**
     * Networks in topology.
     */
    private ArrayList<ContainerNetwork> networks;

    private Set<Device> devices = new HashSet<>();

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device d) {
        if (devices.contains(d)) {
            return;
        }
        devices.add(d);
    }

    /**
     * Instantiates a new Topology.
     *
     * @param routers    the routers
     * @param networks the network top
     */
    public Topology(List<Router> routers, ArrayList<ContainerNetwork> networks) {
        this.routers = routers;
        this.networks = networks;
    }

    /**
     * Instantiates a new Topology.
     *
     * @param networks the network top
     */
    public Topology(ArrayList<ContainerNetwork> networks) {
        this.networks = networks;
    }

    /**
     * Instantiates a new Topology.
     *
     * @param networkCount the network count
     */
    public Topology(Integer networkCount) {
        this.networks = new ArrayList<>(networkCount);
    }

    /**
     * Gets routers.
     *
     * @return the routers
     */
    public List<Router> getRouters() {
        return routers;
    }

    /**
     * Sets routers.
     *
     * @param routers the routers
     */
    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    /**
     * Gets network top.
     *
     * @return the network top
     */
    public ArrayList<ContainerNetwork> getNetworks() {
        return networks;
    }

    /**
     * Sets network top.
     *
     * @param networks the network top
     */
    public void setNetworks(ArrayList<ContainerNetwork> networks) {
        this.networks = networks;
    }

    /**
     * Finds router in list by name.
     * @param name name of router
     * @return router object
     */
    public Router findRouterByName(String name) {
        for (Router r : routers) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topology topology = (Topology) o;
        return getRouters().equals(topology.getRouters()) &&
                getNetworks().equals(topology.getNetworks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRouters(), getNetworks());
    }
}
