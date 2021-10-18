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

package io.patriot_framework.network_simulator.docker.exceptions;

/**
 * Class that represents exception which is thrown when unsupported Topology is passed to a method.
 */
public class UnsupportedTopologyException extends RuntimeException {
    public UnsupportedTopologyException() {
    }

    public UnsupportedTopologyException(String s) {
        super(s);
    }

    public UnsupportedTopologyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UnsupportedTopologyException(Throwable throwable) {
        super(throwable);
    }

    public UnsupportedTopologyException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
