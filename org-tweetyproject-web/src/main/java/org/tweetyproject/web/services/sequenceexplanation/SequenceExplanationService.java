/*
 * This file is part of "TweetyProject", a collection of Java libraries for
 * logical aspects of artificial intelligence and knowledge representation.
 *
 * TweetyProject is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2025 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.web.services.sequenceexplanation;

import org.springframework.stereotype.Service;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.arg.explanations.reasoner.acceptance.DialecticalSequenceExplanationReasoner;
import org.tweetyproject.arg.explanations.semantics.DialectialSequenceExplanation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Dzhychko
 */
@Service
public final class SequenceExplanationService {
    private final DialecticalSequenceExplanationReasoner explanationReasoner = new DialecticalSequenceExplanationReasoner();

    public SequenceExplanations querySequenceExplanations(DungTheory theory, Set<Argument> argumentFilter) {
        var perArgumentSequenceExplanations = new LinkedHashMap<Argument, List<DialectialSequenceExplanation>>();
        for (var argument: theory) {
            if (argumentFilter != null && !argumentFilter.contains(argument)) {
                continue;
            }
            var explanations = explanationReasoner.getExplanations(theory, argument);
            var sequenceExplanations = explanations.stream()
                    .map(explanation -> (DialectialSequenceExplanation) explanation)
                    .collect(Collectors.toUnmodifiableList());
            var allSequenceExplanations = new ArrayList<DialectialSequenceExplanation>();
            perArgumentSequenceExplanations.put(argument, allSequenceExplanations);
            allSequenceExplanations.addAll(sequenceExplanations);
        }
        return new SequenceExplanations(perArgumentSequenceExplanations);
    }

    public static final class SequenceExplanations {
        private final Map<Argument, List<DialectialSequenceExplanation>> perArgumentSequenceExplanations;

        public SequenceExplanations(Map<Argument, List<DialectialSequenceExplanation>> perAtomSequenceExplanations) {
            this.perArgumentSequenceExplanations = perAtomSequenceExplanations;
        }

        public Map<Argument, List<DialectialSequenceExplanation>> getPerArgumentSequenceExplanations() {
            return perArgumentSequenceExplanations;
        }
    }
}
