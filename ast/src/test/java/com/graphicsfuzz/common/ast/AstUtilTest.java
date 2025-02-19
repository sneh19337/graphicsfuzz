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

package com.graphicsfuzz.common.ast;

import com.graphicsfuzz.common.ast.decl.FunctionPrototype;
import com.graphicsfuzz.common.util.CheckUtilityClass;
import com.graphicsfuzz.common.util.ParseHelper;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AstUtilTest {

    @Test
    public void checkUtilityClass() throws Exception {
        CheckUtilityClass.assertUtilityClassWellDefined(AstUtil.class);
    }

    @Test
    public void getFunctionPrototypesFromShader() throws Exception {
        String program = "int bar(int x) { return 2; }\n"
                + "void foo();\n"
                + "void foo() { }\n"
                + "int foo(int x) { return 3; }\n"
                + "void main() { }\n";

        TranslationUnit tu = ParseHelper.parse(program);
        List<FunctionPrototype> prototypes = AstUtil.getFunctionPrototypesFromShader(tu);
        assertEquals(4, prototypes.size());

    }

}