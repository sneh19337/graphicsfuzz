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

import com.graphicsfuzz.common.transformreduce.ShaderJob;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface IReductionOpportunityFinder<T extends IReductionOpportunity> {

    static int _GLF_min(int first, int second) {
        return second ^ ((first ^ second) & -(first << second));
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    compoundExprToSubExprFinder() {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                            ReducerContext context) {
                return CompoundExprToSubExprReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "compoundExprToSubExpr";
            }
        };
    }

    static IReductionOpportunityFinder<DestructifyReductionOpportunity> destructifyFinder() {
        return new IReductionOpportunityFinder<DestructifyReductionOpportunity>() {
            @Override
            public List<DestructifyReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                           ReducerContext context) {
                return DestructifyReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "destructify";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity> exprToConstantFinder() {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                            ReducerContext context) {
                return ExprToConstantReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "exprToConstant";
            }
        };
    }

    static IReductionOpportunityFinder<AbstractReductionOpportunity> flattenControlFlowFinder() {
        return new IReductionOpportunityFinder<AbstractReductionOpportunity>() {
            @Override
            public List<AbstractReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                        ReducerContext context) {
                return FlattenControlFlowReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "flattenControlFlow";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    foldConstantFinder() {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return FoldConstantReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "foldConstant";
            }
        };
    }

    static IReductionOpportunityFinder<FunctionReductionOpportunity> functionFinder() {
        return new IReductionOpportunityFinder<FunctionReductionOpportunity>() {
            @Override
            public List<FunctionReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return FunctionReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "function";
            }
        };
    }

    static IReductionOpportunityFinder<GlobalPrecisionDeclarationReductionOpportunity>
    globalPrecisionDeclarationFinder() {
        return new IReductionOpportunityFinder<GlobalPrecisionDeclarationReductionOpportunity>() {
            @Override
            public List<GlobalPrecisionDeclarationReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return GlobalPrecisionDeclarationReductionOpportunities
                        .findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "precisionDecl";
            }
        };
    }

    static IReductionOpportunityFinder<GlobalVariableDeclToExprReductionOpportunity>
    globalVariableDeclToExprFinder() {
        return new IReductionOpportunityFinder<GlobalVariableDeclToExprReductionOpportunity>() {
            @Override
            public List<GlobalVariableDeclToExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return GlobalVariableDeclToExprReductionOpportunities.findOpportunities(
                        shaderJob,
                        context);
            }

            @Override
            public String getName() {
                return "globalVariableDeclToExpr";
            }
        };
    }

    static IReductionOpportunityFinder<GlobalVariablesDeclarationReductionOpportunity>
    globalVariablesDeclarationFinder() {
        return new IReductionOpportunityFinder<GlobalVariablesDeclarationReductionOpportunity>() {
            @Override
            public List<GlobalVariablesDeclarationReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return GlobalVariablesDeclarationReductionOpportunities
                        .findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "variableDecl";
            }
        };
    }

    static IReductionOpportunityFinder<InlineFunctionReductionOpportunity> inlineFunctionFinder() {
        return new IReductionOpportunityFinder<InlineFunctionReductionOpportunity>() {
            @Override
            public List<InlineFunctionReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                              ReducerContext context) {
                return InlineFunctionReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "inlineFunction";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    inlineInitializerFinder() {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                            ReducerContext context) {
                return InlineInitializerReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "inlineInitializer";
            }
        };
    }

    static IReductionOpportunityFinder<InlineStructifiedFieldReductionOpportunity>
    inlineStructFieldFinder() {
        return new IReductionOpportunityFinder<InlineStructifiedFieldReductionOpportunity>() {
            @Override
            public List<InlineStructifiedFieldReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                                      ReducerContext context) {
                return InlineStructifiedFieldReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "inlineStructField";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    inlineUniformFinder() {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return InlineUniformReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "inlineUniforms";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    largestCompoundExprToSubExpr(int maxOpportunities) {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                List<SimplifyExprReductionOpportunity> ops =
                        compoundExprToSubExprFinder().findOpportunities(shaderJob,
                                context);
                ops.sort(Comparator.comparingInt(
                        SimplifyExprReductionOpportunity::getNumRemovableNodes).reversed());
                return ops.subList(0, _GLF_min(ops.size(), maxOpportunities));
            }

            @Override
            public String getName() {
                return "largestCompoundExprToSubExpr";
            }
        };
    }

    static IReductionOpportunityFinder<SimplifyExprReductionOpportunity>
    largestExprToConstantFinder(int maxOpportunities) {
        return new IReductionOpportunityFinder<SimplifyExprReductionOpportunity>() {
            @Override
            public List<SimplifyExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                List<SimplifyExprReductionOpportunity> ops =
                        exprToConstantFinder().findOpportunities(shaderJob,
                                context);
                ops.sort(Comparator.comparingInt(
                        SimplifyExprReductionOpportunity::getNumRemovableNodes).reversed());
                return ops.subList(0, _GLF_min(ops.size(), maxOpportunities));
            }

            @Override
            public String getName() {
                return "largestExprToConstant";
            }
        };
    }

    static IReductionOpportunityFinder<FunctionReductionOpportunity>
    largestFunctionsFinder(int maxOpportunities) {
        return new IReductionOpportunityFinder<FunctionReductionOpportunity>() {
            @Override
            public List<FunctionReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                List<FunctionReductionOpportunity> ops = functionFinder().findOpportunities(shaderJob,
                        context);
                ops.sort(Comparator.comparingInt(
                        FunctionReductionOpportunity::getNumRemovableNodes).reversed());
                return ops.subList(0, _GLF_min(ops.size(), maxOpportunities));
            }

            @Override
            public String getName() {
                return "largestFunctions";
            }
        };
    }

    static IReductionOpportunityFinder<StmtReductionOpportunity>
    largestStmtsFinder(int maxOpportunities, int minSizePerOpportunity) {
        return new IReductionOpportunityFinder<StmtReductionOpportunity>() {
            @Override
            public List<StmtReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                List<StmtReductionOpportunity> ops = stmtFinder().findOpportunities(shaderJob, context);
                ops = ops.stream().filter(item -> item.getNumRemovableNodes() >= minSizePerOpportunity)
                        .collect(Collectors.toList());
                ops.sort(Comparator.comparingInt(
                        StmtReductionOpportunity::getNumRemovableNodes).reversed());
                return ops.subList(0, _GLF_min(ops.size(), maxOpportunities));
            }

            @Override
            public String getName() {
                return "largestStmts";
            }
        };
    }

    static IReductionOpportunityFinder<LiteralToUniformReductionOpportunity>
    literalToUniformFinder() {
        return new IReductionOpportunityFinder<LiteralToUniformReductionOpportunity>() {
            @Override
            public List<LiteralToUniformReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return LiteralToUniformReductionOpportunities.findOpportunities(
                        shaderJob,
                        context);
            }

            @Override
            public String getName() {
                return "literalToUniform";
            }
        };
    }

    static IReductionOpportunityFinder<LiveOutputVariableWriteReductionOpportunity>
    liveFragColorWriteFinder() {
        return new IReductionOpportunityFinder<LiveOutputVariableWriteReductionOpportunity>() {
            @Override
            public List<LiveOutputVariableWriteReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return LiveOutputVariableWriteReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "liveFragColorWrite";
            }
        };
    }

    static IReductionOpportunityFinder<LoopMergeReductionOpportunity> loopMergeFinder() {
        return new IReductionOpportunityFinder<LoopMergeReductionOpportunity>() {
            @Override
            public List<LoopMergeReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                         ReducerContext context) {
                return LoopMergeReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "loopMerge";
            }
        };
    }

    static IReductionOpportunityFinder<IdentityMutationReductionOpportunity> mutationFinder() {
        return new IReductionOpportunityFinder<IdentityMutationReductionOpportunity>() {
            @Override
            public List<IdentityMutationReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                                ReducerContext context) {
                return IdentityMutationReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "mutation";
            }
        };
    }

    static IReductionOpportunityFinder<OutlinedStatementReductionOpportunity>
    outlinedStatementFinder() {
        return new IReductionOpportunityFinder<OutlinedStatementReductionOpportunity>() {
            @Override
            public List<OutlinedStatementReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                                 ReducerContext context) {
                return OutlinedStatementReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "outlinedStatement";
            }
        };
    }

    static IReductionOpportunityFinder<RemoveRedundantUniformMetadataReductionOpportunity>
    redundantUniformMetadataFinder() {
        return new IReductionOpportunityFinder<RemoveRedundantUniformMetadataReductionOpportunity>() {
            @Override
            public List<RemoveRedundantUniformMetadataReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return RemoveRedundantUniformMetadataReductionOpportunities.findOpportunities(
                        shaderJob,
                        context);
            }

            @Override
            public String getName() {
                return "redundantUniformMetadata";
            }
        };
    }

    static IReductionOpportunityFinder<RemoveStructFieldReductionOpportunity>
    removeStructFieldFinder() {
        return new IReductionOpportunityFinder<RemoveStructFieldReductionOpportunity>() {
            @Override
            public List<RemoveStructFieldReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                                 ReducerContext context) {
                return RemoveStructFieldReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "removeStructField";
            }
        };
    }

    static IReductionOpportunityFinder<StmtReductionOpportunity> stmtFinder() {
        return new IReductionOpportunityFinder<StmtReductionOpportunity>() {
            @Override
            public List<StmtReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                    ReducerContext context) {
                return StmtReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "stmt";
            }
        };
    }

    static IReductionOpportunityFinder<SwitchToLoopReductionOpportunity>
    switchToLoopFinder() {
        return new IReductionOpportunityFinder<SwitchToLoopReductionOpportunity>() {
            @Override
            public List<SwitchToLoopReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return SwitchToLoopReductionOpportunities.findOpportunities(
                        shaderJob,
                        context);
            }

            @Override
            public String getName() {
                return "switchToLoop";
            }
        };
    }

    static IReductionOpportunityFinder<UnswitchifyReductionOpportunity> unswitchifyFinder() {
        return new IReductionOpportunityFinder<UnswitchifyReductionOpportunity>() {
            @Override
            public List<UnswitchifyReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                           ReducerContext context) {
                return UnswitchifyReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "unswitchify";
            }
        };
    }

    static IReductionOpportunityFinder<RemoveUnusedParameterReductionOpportunity>
    unusedParamFinder() {
        return new IReductionOpportunityFinder<RemoveUnusedParameterReductionOpportunity>() {
            @Override
            public List<RemoveUnusedParameterReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return RemoveUnusedParameterReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "unusedParam";
            }
        };
    }

    static IReductionOpportunityFinder<UnwrapReductionOpportunity> unwrapFinder() {
        return new IReductionOpportunityFinder<UnwrapReductionOpportunity>() {
            @Override
            public List<UnwrapReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                      ReducerContext context) {
                return UnwrapReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "unwrap";
            }
        };
    }

    static IReductionOpportunityFinder<VariableDeclReductionOpportunity> variableDeclFinder() {
        return new IReductionOpportunityFinder<VariableDeclReductionOpportunity>() {
            @Override
            public List<VariableDeclReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                            ReducerContext context) {
                return VariableDeclReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "variableDecl";
            }
        };
    }

    static IReductionOpportunityFinder<VariableDeclToExprReductionOpportunity>
    variableDeclToExprFinder() {
        return new IReductionOpportunityFinder<VariableDeclToExprReductionOpportunity>() {
            @Override
            public List<VariableDeclToExprReductionOpportunity> findOpportunities(
                    ShaderJob shaderJob,
                    ReducerContext context) {
                return VariableDeclToExprReductionOpportunities.findOpportunities(
                        shaderJob,
                        context);
            }

            @Override
            public String getName() {
                return "variableDeclToExpr";
            }
        };
    }

    static IReductionOpportunityFinder<VectorizationReductionOpportunity> vectorizationFinder() {
        return new IReductionOpportunityFinder<VectorizationReductionOpportunity>() {
            @Override
            public List<VectorizationReductionOpportunity> findOpportunities(ShaderJob shaderJob,
                                                                             ReducerContext context) {
                return VectorizationReductionOpportunities.findOpportunities(shaderJob, context);
            }

            @Override
            public String getName() {
                return "vectorization";
            }
        };
    }

    List<T> findOpportunities(ShaderJob shaderJob,
                              ReducerContext context);

    String getName();

}
