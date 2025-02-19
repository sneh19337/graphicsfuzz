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

package com.graphicsfuzz.reducer.reductionopportunities;

import com.graphicsfuzz.common.ast.TranslationUnit;
import com.graphicsfuzz.common.glslversion.ShadingLanguageVersion;
import com.graphicsfuzz.common.util.IdGenerator;
import com.graphicsfuzz.common.util.ParseHelper;
import com.graphicsfuzz.common.util.RandomWrapper;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnswitchifyReductionOpportunitiesTest {

    @Test
    public void testNotInjected() throws Exception {
        final String program = "void foo(int x) { switch(x) { case 0: default: break; } }";
        final TranslationUnit tu = ParseHelper.parse(program);
        List<UnswitchifyReductionOpportunity> ops = UnswitchifyReductionOpportunities
                .findOpportunities(MakeShaderJobFromFragmentShader.make(tu),
                        new ReducerContext(false,
                                ShadingLanguageVersion.GLSL_130,
                                new RandomWrapper(0),
                                new IdGenerator()));
        assertEquals(0, ops.size());
    }

}
