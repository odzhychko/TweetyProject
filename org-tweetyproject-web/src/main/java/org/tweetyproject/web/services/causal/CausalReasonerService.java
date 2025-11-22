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
package org.tweetyproject.web.services.causal;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.explanations.reasoner.acceptance.DialecticalSequenceExplanationReasoner;
import org.tweetyproject.arg.explanations.semantics.DialectialSequenceExplanation;
import org.tweetyproject.causal.reasoner.ArgumentationBasedCausalReasoner;
import org.tweetyproject.causal.syntax.CausalKnowledgeBase;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.Proposition;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Dzhychko
 */
@Service
public final class CausalReasonerService {
    private final ArgumentationBasedCausalReasoner causalReasoner = new ArgumentationBasedCausalReasoner();
    private final DialecticalSequenceExplanationReasoner explanationReasoner = new DialecticalSequenceExplanationReasoner();

    public Collection<PlFormula> queryConclusions(CausalKnowledgeBase causalKnowledgeBase, Collection<PlFormula> observations, Set<Proposition> conclusionFilter) {
        Collection<PlFormula> conclusions = causalReasoner.getConclusions(causalKnowledgeBase, observations);
        conclusions = filterConclusions(conclusions, conclusionFilter);
        return conclusions;
    }

    private static Collection<PlFormula> filterConclusions(Collection<PlFormula> conclusions, @Nullable Set<Proposition> conclusionFilter) {
        if (conclusionFilter == null) {
            return conclusions;
        }
        return conclusions.stream()
                .filter(formula -> isConclusionInFilter(formula, conclusionFilter))
                .collect(Collectors.toUnmodifiableList());
    }

    public static boolean isConclusionInFilter(PlFormula conclusion, @NonNull Set<Proposition> conclusionFilter) {
        return conclusionFilter.stream()
                .anyMatch(proposition -> conclusion.getAtoms().contains(proposition));
    }


    public Map<Proposition, Collection<Proposition>> queryPerAtomSignificantAtoms(CausalKnowledgeBase causalKnowledgeBase, Collection<PlFormula> observations, Set<Proposition> conclusionFilter) {
        return causalReasoner.getSignificantAtoms(causalKnowledgeBase, observations, Map.of(), conclusionFilter);
    }

    public CausalReasonerService.SequenceExplanations querySequenceExplanations(CausalKnowledgeBase causalKnowledgeBase, Collection<PlFormula> observations, Set<Proposition> conclusionFilter) {
        var theory = causalReasoner.getInducedTheory(causalKnowledgeBase, observations, Map.of());
        var perAtomArgumentsWithAtomInConclusion = causalReasoner.getPerAtomArgumentsWithAtomInConclusion(theory, conclusionFilter);

        var perAtomPerSequenceExplanations = new LinkedHashMap<Proposition, List<DialectialSequenceExplanation>>();
        for (var atomWithArgumentsAtomInConclusion : perAtomArgumentsWithAtomInConclusion.entrySet()) {
            var atom = atomWithArgumentsAtomInConclusion.getKey();
            var allSequenceExplanations = new ArrayList<DialectialSequenceExplanation>();
            perAtomPerSequenceExplanations.put(atom, allSequenceExplanations);
            for (var argument : atomWithArgumentsAtomInConclusion.getValue().stream().findFirst().stream().collect(Collectors.toUnmodifiableList())) {
                var explanations = explanationReasoner.getExplanations(theory, argument);
                var sequenceExplanations = explanations.stream()
                        .map(explanation -> (DialectialSequenceExplanation) explanation)
                        .collect(Collectors.toUnmodifiableList());
                allSequenceExplanations.addAll(sequenceExplanations);
            }
        }
        return new CausalReasonerService.SequenceExplanations(theory.getAttacks(), perAtomPerSequenceExplanations);
    }

    public static final class SequenceExplanations {
        private final Set<Attack> attacks;
        private final Map<Proposition, List<DialectialSequenceExplanation>> perAtomSequenceExplanations;

        public SequenceExplanations(Set<Attack> attacks,
                                    Map<Proposition, List<DialectialSequenceExplanation>> perAtomSequenceExplanations) {
            this.attacks = attacks;
            this.perAtomSequenceExplanations = perAtomSequenceExplanations;
        }

        public Set<Attack> getAttacks() {
            return attacks;
        }

        public Map<Proposition, List<DialectialSequenceExplanation>> getPerAtomSequenceExplanations() {
            return perAtomSequenceExplanations;
        }
    }
}
