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


import io.patriot_framework.network_simulator.docker.manager.Manager;
import io.patriot_framework.network_simulator.docker.model.Topology;
import io.patriot_framework.network_simulator.docker.model.devices.router.Router;
import io.patriot_framework.network_simulator.docker.model.devices.router.RouterImpl;
import io.patriot_framework.network_simulator.docker.model.network.ContainerNetwork;
import io.patriot_framework.network_simulator.docker.model.routes.CalcRoute;
import io.patriot_framework.network_simulator.docker.model.routes.NextHop;
import io.patriot_framework.network_simulator.docker.topology.ContainerTopologyManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FloydWarshallTest {

    private ArrayList<ContainerNetwork> copyTopology(ArrayList<ContainerNetwork> topology) {
        ArrayList<ContainerNetwork> resultTop = new ArrayList<>();
        for (int i = 0; i < topology.size(); i++) {
            ContainerNetwork n = new ContainerNetwork();
            n.setInternet(topology.get(i).getInternet());
            n.setName(topology.get(i).getName());
            n.setMask(topology.get(i).getMask());
            n.setIPAddress(topology.get(i).getIPAddress());
            resultTop.add(n);
        }
        return resultTop;
    }

    private ArrayList<ContainerNetwork> prepareResultTopology(ArrayList<ContainerNetwork> topology) {
        ArrayList<ContainerNetwork> resultTop = copyTopology(topology);
        CalculatedRouteList n1Routes = new CalculatedRouteList();
        n1Routes.add(new CalcRoute(new NextHop(null, 0), null));
        n1Routes.add(new CalcRoute(new NextHop(null, 1), 1));
        n1Routes.add(new CalcRoute(new NextHop(null, 1), 2));
        n1Routes.add(new CalcRoute(new NextHop(null, 1), 2));
        n1Routes.add(new CalcRoute(new NextHop(null, 1), 3));
        resultTop.get(0).setCalcRoutes(n1Routes);

        CalculatedRouteList n2Routes = new CalculatedRouteList();
        n2Routes.add(new CalcRoute(new NextHop(null, 0), 1));
        n2Routes.add(new CalcRoute(new NextHop(null, 1), null));
        n2Routes.add(new CalcRoute(new NextHop(null, 2), 1));
        n2Routes.add(new CalcRoute(new NextHop(null, 3), 1));
        n2Routes.add(new CalcRoute(new NextHop(null, 2), 2));
        resultTop.get(1).setCalcRoutes(n2Routes);

        CalculatedRouteList n3Routes = new CalculatedRouteList();
        n3Routes.add(new CalcRoute(new NextHop(null, 1), 2));
        n3Routes.add(new CalcRoute(new NextHop(null, 1), 1));
        n3Routes.add(new CalcRoute(new NextHop(null, 2), null));
        n3Routes.add(new CalcRoute(new NextHop(null, 3), 1));
        n3Routes.add(new CalcRoute(new NextHop(null, 4), 1));
        resultTop.get(2).setCalcRoutes(n3Routes);

        CalculatedRouteList n4Routes = new CalculatedRouteList();
        n4Routes.add(new CalcRoute(new NextHop(null, 1), 2));
        n4Routes.add(new CalcRoute(new NextHop(null, 1), 1));
        n4Routes.add(new CalcRoute(new NextHop(null, 2), 1));
        n4Routes.add(new CalcRoute(new NextHop(null, 3), null));
        n4Routes.add(new CalcRoute(new NextHop(null, 4), 1));
        resultTop.get(3).setCalcRoutes(n4Routes);

        CalculatedRouteList n5Routes = new CalculatedRouteList();
        n5Routes.add(new CalcRoute(new NextHop(null, 2), 3));
        n5Routes.add(new CalcRoute(new NextHop(null, 2), 2));
        n5Routes.add(new CalcRoute(new NextHop(null, 2), 1));
        n5Routes.add(new CalcRoute(new NextHop(null, 3), 1));
        n5Routes.add(new CalcRoute(new NextHop(null, 4), null));
        resultTop.get(4).setCalcRoutes(n5Routes);

        return resultTop;
    }

    @Test
    public void FloydWarshallTest() {

        ArrayList<ContainerNetwork> containerNetworks = new ArrayList<>(4);
        ArrayList<Router> routers = new ArrayList<>();


        routers.add(new RouterImpl("R1"));
        routers.add(new RouterImpl("R2"));
        routers.add(new RouterImpl("R3"));
        routers.add(new RouterImpl("R5"));

        ContainerNetwork n1 = new ContainerNetwork();
        n1.setIPAddress("192.168.0.0");
        n1.setName("TN1");
        n1.setMask(28);

        ContainerNetwork n2 = new ContainerNetwork();
        n2.setMask(28);
        n2.setIPAddress("192.168.16.0");
        n2.setName("TN2");

        ContainerNetwork n3 = new ContainerNetwork();
        n3.setName("TN3");
        n3.setMask(28);
        n3.setIPAddress("192.168.32.0");

        ContainerNetwork n4 = new ContainerNetwork();
        n4.setMask(28);
        n4.setIPAddress("192.168.48.0");
        n4.setName("TN4");

        ContainerNetwork internet = new ContainerNetwork();
        internet.setInternet(true);

        containerNetworks.addAll(Arrays.asList(n1, n2, n3, n4, internet));
        Topology topology = new Topology(routers, containerNetworks);

        initNetworks(containerNetworks, routers);
        ContainerTopologyManager networkManager = new ContainerTopologyManager("patriotRouter");
        ArrayList<ContainerNetwork> resArr = prepareResultTopology(containerNetworks);
        networkManager.calcRoutes(topology);

        for (int i = 0; i < 5; i++) {
            ContainerNetwork targetTopologyNetwork = containerNetworks.get(i);
            ContainerNetwork resultTopologyNetwork = resArr.get(i);
            for (int y = 0; y < 5; y++) {
                Assertions.assertEquals(targetTopologyNetwork.getCalcRoutes().get(y).getCost(),
                        resultTopologyNetwork.getCalcRoutes().get(y).getCost());

                Assertions.assertEquals(targetTopologyNetwork.getCalcRoutes().get(y).getNextHop().getNetwork(),
                        resultTopologyNetwork.getCalcRoutes().get(y).getNextHop().getNetwork());
            }
        }

    }


    private void initNetworks(ArrayList<ContainerNetwork> topology, ArrayList<Router> routers) {

        Integer routNeedCalc = topology.size() + 1;
        ContainerNetwork tN1 = topology.get(0);
        ContainerNetwork tN2 = topology.get(1);
        ContainerNetwork tN3 = topology.get(2);
        ContainerNetwork tN4 = topology.get(3);
        ContainerNetwork internet = topology.get(4);

        tN1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), null));
        tN1.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(0), 1), 1));
        tN1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 2), routNeedCalc));
        tN1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 3), routNeedCalc));
        tN1.getCalcRoutes().add(new CalcRoute(new NextHop(null, 4), routNeedCalc));

        tN2.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(0), 0), 1));
        tN2.getCalcRoutes().add(new CalcRoute(new NextHop(null, 1), null));
        tN2.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(1), 2), 1));
        tN2.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(2), 3), 1));
        tN2.getCalcRoutes().add(new CalcRoute(new NextHop(null, 4), routNeedCalc));

        tN3.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), routNeedCalc));
        tN3.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(1), 1), 1));
        tN3.getCalcRoutes().add(new CalcRoute(new NextHop(null, 2), null));
        tN3.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 3), 1));
        tN3.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 4), 1));

        tN4.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), routNeedCalc));
        tN4.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(2), 1), 1));
        tN4.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 2), 1));
        tN4.getCalcRoutes().add(new CalcRoute(new NextHop(null, 3), null));
        tN4.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 4), 1));

        internet.getCalcRoutes().add(new CalcRoute(new NextHop(null, 0), routNeedCalc));
        internet.getCalcRoutes().add(new CalcRoute(new NextHop(null, 1), routNeedCalc));
        internet.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 2), 1));
        internet.getCalcRoutes().add(new CalcRoute(new NextHop(routers.get(3), 3), 1));
        internet.getCalcRoutes().add(new CalcRoute(new NextHop(null, 4), null));
    }
}
