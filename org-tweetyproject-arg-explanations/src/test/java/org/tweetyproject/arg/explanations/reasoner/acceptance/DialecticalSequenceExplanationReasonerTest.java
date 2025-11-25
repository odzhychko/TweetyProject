/*
 *  This file is part of "TweetyProject", a collection of Java libraries for
 *  logical aspects of artificial intelligence and knowledge representation.
 *
 *  TweetyProject is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License version 3 as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright 2025 The TweetyProject Team <http://tweetyproject.org/contact/>
 */

package org.tweetyproject.arg.explanations.reasoner.acceptance;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Oleksandr Dzhychko
 */
class DialecticalSequenceExplanationReasonerTest {

    DialecticalSequenceExplanationReasoner reasoner = new DialecticalSequenceExplanationReasoner();

    @Disabled("Reasoner fails to provide explanations.")
    @Test
    public void circleOfFourArguments() {
        var argumentA = new Argument("a");
        var argumentB = new Argument("b");
        var argumentC = new Argument("c");
        var argumentD = new Argument("d");
        var attackAB = new Attack(argumentA, argumentB);
        var attackBC = new Attack(argumentB, argumentC);
        var attackCD = new Attack(argumentC, argumentD);
        var attackDA = new Attack(argumentD, argumentA);

        var theory = new DungTheory();
        theory.add(argumentA, argumentB, argumentC);
        theory.add(attackAB, attackBC,  attackCD, attackDA);

        // #getExplanations fails with `java.lang.IndexOutOfBoundsException: Index: 1, Size: 1`
        var explanations = reasoner.getExplanations(theory, argumentA);

        fail("Missing assertion.");
    }

}