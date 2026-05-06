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

import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.web.services.sequenceexplanation.AttackDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Dzhychko
 */
public final class ArgumentationFrameworkReply {
    private final List<String> arguments;
    private final List<AttackDTO> attacks;

    public ArgumentationFrameworkReply(List<String> arguments, List<AttackDTO> attacks) {
        this.arguments = arguments;
        this.attacks = attacks;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public List<AttackDTO> getAttacks() {
        return attacks;
    }

    public static ArgumentationFrameworkReply from(DungTheory argumentationFramework) {
        var arguments = argumentationFramework.stream()
                .map(Argument::getName)
                .collect(Collectors.toUnmodifiableList());
        var attacksConverted = AttackDTO.from(argumentationFramework.getAttacks());
        return new ArgumentationFrameworkReply(arguments, attacksConverted);
    }
}

