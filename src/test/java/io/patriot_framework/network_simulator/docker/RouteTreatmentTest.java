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

import io.patriot_framework.network.simulator.api.manager.Manager;
import io.patriot_framework.network_simulator.docker.model.Topology;
import io.patriot_framework.network_simulator.docker.model.devices.router.NetworkInterface;
import io.patriot_framework.network_simulator.docker.model.devices.router.Router;
import io.patriot_framework.network_simulator.docker.model.devices.router.RouterImpl;
import io.patriot_framework.network_simulator.docker.model.network.TopologyNetwork;
import io.patriot_framework.network_simulator.docker.model.routes.CalcRoute;
import io.patriot_framework.network_simulator.docker.model.routes.NextHop;
import io.patriot_framework.network_simulator.docker.model.routes.Route;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTreatmentTest {

    private Manager manager = new Manager("patriot");

    @Test
    public void testRouteTreatmentTest() {
        ArrayList<TopologyNetwork> topologyNetworks = new ArrayList<>();
        Topology topology = new Topology(topologyNetworks);
        Router r = new RouterImpl("TestRouter", "Docker");
        Route route = createSimpleTopology(topologyNetworks, false, r);
        manager.processRoutes(topology);
        assertTrue(manager.getProcessedRoutes().get(r.getName()).get(0).toAPIFormat().equals(route.toAPIFormat()));

    }

    private Route parseRoute(TopologyNetwork source, TopologyNetwork dest, Router targetRouter, NetworkInterface targetInterface) {
        Route route = new Route();
        route.setrNetworkInterface(targetInterface);
        route.setSource(source);
        route.setDest(dest);
        route.setTargetRouter(targetRouter);
        return route;
    }

    private Route createSimpleTopology(ArrayList<TopologyNetwork> topology, Boolean duplicate, Router r) {
        List<NetworkInterface> routerInterfaces = new ArrayList<>();
        routerInterfaces.add(new NetworkInterface("eth0", "192.168.0.2", 24));
        r.setNetworkInterfaces(routerInterfaces);

        TopologyNetwork n1 = new TopologyNetwork();
        n1.setName("TestNetwork1");
        n1.setIPAddress("172.16.0.0");
        n1.setCreator("Docker");
        n1.setMask(16);

        TopologyNetwork n2 = new TopologyNetwork();
        n2.setName("TestNetwork2");
        n2.setIPAddress("192.168.0.0");
        n2.setCreator("Docker");
        n2.setMask(24);

        n1.setCalcRoutes(createCalculatedRouteList(r, 0, 1));

        topology.add(n1);
        topology.add(n2);
        return parseRoute(n1, n2, r, routerInterfaces.get(0));
    }

    private CalculatedRouteList createCalculatedRouteList(Router r, Integer sourceNetwork, Integer destNetwork) {
        CalcRoute calcRoute = new CalcRoute(new NextHop(r, destNetwork), 1);
        CalculatedRouteList calculatedRouteList = new CalculatedRouteList();
        calculatedRouteList.add(destNetwork, calcRoute);
        calculatedRouteList.add(sourceNetwork, new CalcRoute(new NextHop(null, sourceNetwork), null));
        return calculatedRouteList;
    }

    private ArrayList<TopologyNetwork> prepareComplicatedTopology(ArrayList<Router> routers) {
        ArrayList<TopologyNetwork> topology = new ArrayList<>();

        TopologyNetwork n1 = new TopologyNetwork();
        n1.setName("TestNetwork1");
        n1.setIPAddress("192.168.1.0");
        n1.setCreator("Docker");
        n1.setMask(28);

        TopologyNetwork n2 = new TopologyNetwork();
        n2.setName("TestNetwork2");
        n2.setIPAddress("192.168.1.16");
        n2.setCreator("Docker");
        n2.setMask(28);

        TopologyNetwork n3 = new TopologyNetwork();
        n3.setName("TestNetwork3");
        n3.setIPAddress("192.168.1.32");
        n3.setCreator("Docker");
        n3.setMask(28);
        topology.addAll(Arrays.asList(n1, n2, n3));

        prepareOnStartTopology(topology, routers);
        return topology;
    }

    private void prepareOnStartTopology(ArrayList<TopologyNetwork> topology, ArrayList<Router> routers) {
        Integer routNeedCalc = topology.size() + 1;
        TopologyNetwork n1 = topology.get(0);
        TopologyNetwork n2 = topology.get(1);
        TopologyNetwork n3 = topology.get(2);

        n1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), null));
        n1.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(0), 1), 1));
        n1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 2), routNeedCalc));


        n2.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(0), 0), 1));
        n2.getCalcRoutes().add(new CalcRoute(new NextHop(null, 1), null));
        n2.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(1), 2), 1));


        n3.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), routNeedCalc));
        n3.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(1), 1), 1));
        n3.getCalcRoutes().add(new CalcRoute(new NextHop(null, 2), null));

    }

}
