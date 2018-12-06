package io.patriot_framework.network_simulator.example.network;

import io.patriot_framework.network_simulator.example.container.DockerContainer;
import io.patriot_framework.network_simulator.example.manager.DockerManager;

/**
 * The type Docker network utilities.
 */
public class DockerNetworkUtilities {

    /**
     * Sets default gw.
     *
     * @param container the container
     * @param gateway   the gateway
     * @param manager   the manager
     */
    public void setDefaultGw(DockerContainer container, String gateway, DockerManager manager) {
        manager.runCommand(container, "route del default");
        manager.runCommand(container, "route add default gw " + gateway);
    }
}