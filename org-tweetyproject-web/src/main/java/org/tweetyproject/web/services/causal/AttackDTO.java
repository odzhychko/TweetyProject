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

import org.tweetyproject.arg.dung.syntax.Attack;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleksandr Dzhychko
 */
public final class AttackDTO {
    private final String attacker;
    private final String attacked;

    public AttackDTO(String attacker, String attacked) {
        this.attacker = attacker;
        this.attacked = attacked;
    }

    public static List<AttackDTO> from(Collection<Attack> attacks) {
        return attacks.stream()
                .map(AttackDTO::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public static AttackDTO from(Attack attack) {
        return new AttackDTO(attack.getAttacker().toString(), attack.getAttacked().toString());
    }


    public String getAttacker() {
        return attacker;
    }

    public String getAttacked() {
        return attacked;
    }
}
