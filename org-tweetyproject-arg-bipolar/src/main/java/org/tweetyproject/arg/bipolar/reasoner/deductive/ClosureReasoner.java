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
import org.tweetyproject.commons.util.SetTools;

import java.util.*;

/**
 * a set of arguments S is closed under the support relation iff all arguments supported by an element of S are in S.
 *
 * @author Lars Bengel
 *
 */
public class ClosureReasoner extends AbstractBipolarExtensionReasoner {

	/**
	 *
	 * Return models
	 * @param bbase argumentation framework
	 * @return models
	 */
    public Collection<Collection<BArgument>> getModels(DeductiveArgumentationFramework bbase) {
        Set<Collection<BArgument>> extensions = new HashSet<>();
        // Check all subsets
        for(Set<BArgument> ext: new SetTools<BArgument>().subsets(bbase))
            if(bbase.isClosed(new ArgumentSet(ext)))
                extensions.add(new ArgumentSet(ext));
        return extensions;
    }

    /**
     *  Returns a model
     * @param bbase a belief base
     * @return a ArgumentSet
     */
    public ArgumentSet getModel(DeductiveArgumentationFramework bbase) {
        // as the empty set is always closed we return that one.
        return new ArgumentSet();
    }

    /** Default Constructor */
    public ClosureReasoner(){}

    @Override
    public Collection<Collection<BArgument>> getModels(AbstractBipolarFramework baf) {
        if (baf instanceof DeductiveArgumentationFramework) {
            return this.getModels((DeductiveArgumentationFramework) baf);
        } else {
            throw new IllegalArgumentException("Unsupported Framework type");
        }
    }
}
