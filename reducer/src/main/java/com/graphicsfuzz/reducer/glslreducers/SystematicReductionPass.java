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

package com.graphicsfuzz.reducer.glslreducers;

import com.graphicsfuzz.common.transformreduce.ShaderJob;
import com.graphicsfuzz.reducer.reductionopportunities.IReductionOpportunity;
import com.graphicsfuzz.reducer.reductionopportunities.IReductionOpportunityFinder;
import com.graphicsfuzz.reducer.reductionopportunities.ReducerContext;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SystematicReductionPass extends AbstractReductionPass {

    private final int maximumGranularity;
    private boolean isInitialized;
    private int index;
    private int granularity;

    public SystematicReductionPass(
            ReducerContext reducerContext,
            boolean verbose,
            IReductionOpportunityFinder<? extends IReductionOpportunity> finder,
            int maximumGranularity
    ) {
        // Ignore verbose argument for now.
        super(reducerContext, finder);
        this.isInitialized = false;
        this.maximumGranularity = maximumGranularity;
    }

    public SystematicReductionPass(
            ReducerContext reducerContext,
            boolean verbose,
            IReductionOpportunityFinder<? extends IReductionOpportunity> finder) {
        this(reducerContext, verbose, finder, Integer.MAX_VALUE);
    }

    @Override
    public void notifyInteresting(boolean interesting) {
        if (!interesting) {
            index += granularity;
        }
    }

    @Override
    public boolean reachedMinimumGranularity() {
        if (!isInitialized) {
            // Conceptually we can think that if the pass has not yet been initialized, it is operating
            // at unbounded granularity.
            return false;
        }
        assert granularity != 0;
        return granularity == 1;
    }

    @Override
    public void replenish() {
        throw new UnsupportedOperationException(
                "Replenishing is not supported by this kind of reduction pass.");
    }

    @Override
    public String toString() {
        return (isInitialized ? "granularity: " + granularity + ", index: " + index : "uninitialized");
    }

    @Override
    public Optional<ShaderJob> tryApplyReduction(ShaderJob shaderJob) {
        final ShaderJob workingShaderJob = shaderJob.clone();
        List<? extends IReductionOpportunity> opportunities =
                getFinder().findOpportunities(workingShaderJob, getReducerContext());

        opportunities.sort(Comparator.comparing(IReductionOpportunity::depth));

        if (!isInitialized) {
            isInitialized = true;
            index = 0;
            granularity = _GLF_min(maximumGranularity, _GLF_max(1, opportunities.size()));
        }

        assert granularity > 0;

        if (index >= opportunities.size()) {
            index = 0;
            granularity = _GLF_max(1, granularity / 2);
            return Optional.empty();
        }


        for (int i = index; i < _GLF_min(index + granularity, opportunities.size()); i++) {
            opportunities.get(i).applyReduction();
        }

        return Optional.of(workingShaderJob);
    }

    private int _GLF_max(int first, int second) {
        return first ^ ((first ^ second) & -(first << second));
    }

    private int _GLF_min(int first, int second) {
        return second ^ ((first ^ second) & -(first << second));
    }
}
