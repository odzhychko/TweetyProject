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
 *  Copyright 2016 The TweetyProject Team <http://tweetyproject.org/contact/>
 */

package org.tweetyproject.arg.bipolar.reasoner.deductive;

import org.tweetyproject.arg.bipolar.reasoner.AbstractBipolarExtensionReasoner;
import org.tweetyproject.arg.bipolar.syntax.*;

import java.util.*;

/**
 * reasoner for admissibility in bipolar argumentation frameworks with deductive support interpretation
 * a set of arguments is d-admissible iff it is admissible wrt. the complex attacks in the framework
 */
public class DAdmissibleReasoner extends AbstractBipolarExtensionReasoner {
	/**
	 *
	 * Return models
	 * @param bbase argumentation framework
	 * @return models
	 */
    public Collection<Collection<BArgument>> getModels(DeductiveArgumentationFramework bbase) {
        Collection<Collection<BArgument>> extensions = new HashSet<>();
        for (Collection<BArgument> ext: new ConflictFreeReasoner().getModels(bbase)) {
            for (BArgument arg: ext) {
                boolean defending = true;
                for (BipolarEntity attacker: bbase.getAttackers(arg)) {
                    if (!bbase.isAttacked((BArgument) attacker, new HashSet<>(ext))) {
                        defending = false;
                        break;
                    }
                }
                if (defending) {
                    extensions.add(ext);
                }
            }
        }
        return extensions;
    }

    /** Default Constructor */
    public DAdmissibleReasoner(){}

    @Override
    public Collection<Collection<BArgument>> getModels(AbstractBipolarFramework baf) {
        if (baf instanceof DeductiveArgumentationFramework) {
            return this.getModels((DeductiveArgumentationFramework) baf);
        } else {
            throw new IllegalArgumentException("Unsupported Framework type");
        }
    }
}
