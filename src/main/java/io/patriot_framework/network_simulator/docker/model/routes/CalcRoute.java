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


import java.util.Objects;

/**
 * Calculated route. Including all necessary info to describe routes.
 */
public class CalcRoute {
    private NextHop nextHop;
    private Integer cost;

    /**
     * Instantiates a new Calc route.
     *
     * @param nextHop the next hop
     * @param cost    the cost
     */
    public CalcRoute(NextHop nextHop, Integer cost) {
        this.nextHop = nextHop;
        this.cost = cost;
    }

    /**
     * Gets next hop.
     *
     * @return the next hop
     */
    public NextHop getNextHop() {
        return nextHop;
    }

    /**
     * Sets next hop.
     *
     * @param nextHop the next hop
     */
    public void setNextHop(NextHop nextHop) {
        this.nextHop = nextHop;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcRoute calcRoute = (CalcRoute) o;
        return getNextHop().equals(calcRoute.getNextHop()) &&
                Objects.equals(getCost(), calcRoute.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNextHop(), getCost());
    }
}
