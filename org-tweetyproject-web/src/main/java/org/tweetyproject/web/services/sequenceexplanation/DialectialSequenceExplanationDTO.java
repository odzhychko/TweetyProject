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

import org.tweetyproject.arg.explanations.semantics.DialectialSequenceExplanation;
import org.tweetyproject.web.services.causal.ArgumentSerialization;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Dzhychko
 */
public final class DialectialSequenceExplanationDTO {
    private final String argument;
    private final List<List<String>> supporters;
    private final List<List<String>> defeated;

    public DialectialSequenceExplanationDTO(String argument, List<List<String>> supporters, List<List<String>> defeated) {
        this.argument = argument;
        this.supporters = supporters;
        this.defeated = defeated;
    }

    public String getArgument() {
        return argument;
    }

    public List<List<String>> getSupporters() {
        return supporters;
    }

    public List<List<String>> getDefeated() {
        return defeated;
    }

    public static DialectialSequenceExplanationDTO from(DialectialSequenceExplanation explanation) {
        return new DialectialSequenceExplanationDTO(
                explanation.getArgument().toString(),
                ArgumentSerialization.fromCollectionOfCollections(explanation.getSupporters()),
                ArgumentSerialization.fromCollectionOfCollections(explanation.getDefeated())
        );
    }

    public static List<DialectialSequenceExplanationDTO> from(List<DialectialSequenceExplanation> explanations) {
        return explanations.stream().map(DialectialSequenceExplanationDTO::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
