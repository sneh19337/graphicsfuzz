/*
 * Copyright 2018 The GraphicsFuzz Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.graphicsfuzz.server.webui;

public final class WebUiConstants {

    static final String FILE_ROUTE = "file";
    static final String WORKER_DIR = "processing";
    static final String SHADER_FAMILIES_DIR = "shaderfamilies";
    static final String WORKER_INFO_FILE = "client.json";
    static final String COMPUTE_SHADER_DOC_URL =
            "https://github.com/google/graphicsfuzz/blob/master/docs/glsl-fuzz-walkthrough"
                    + ".md#Inspecting-results-for-compute-shader-families";
    private WebUiConstants() {
        // Utility class
    }

}
