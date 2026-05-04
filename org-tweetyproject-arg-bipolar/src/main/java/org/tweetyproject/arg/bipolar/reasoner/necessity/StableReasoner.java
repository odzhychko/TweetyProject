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
package org.tweetyproject.arg.bipolar.reasoner.necessity;

import org.tweetyproject.arg.bipolar.reasoner.AbstractBipolarExtensionReasoner;
import org.tweetyproject.arg.bipolar.syntax.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * a set of arguments S is stable iff the set of arguments deactivated by S equals A\S,
 * where A is the set of all arguments in the argumentation framework.
 *
 * @author Lars Bengel
 *
 */
public class StableReasoner extends AbstractBipolarExtensionReasoner {
	/**
	 * 
	 * @param bbase argumentation framework
	 * @return models
	 */
    public Collection<Collection<BArgument>> getModels(NecessityArgumentationFramework bbase) {
        Collection<Collection<BArgument>> preferredExtensions = new PreferredReasoner().getModels(bbase);
        Set<Collection<BArgument>> result = new HashSet<>();
        for(Collection<BArgument> ext: preferredExtensions){
            Set<BArgument> deactivatedArguments = bbase.getDeactivatedArguments(ext);
            Set<BArgument> arguments = new HashSet<>(bbase);
            arguments.removeAll(ext);
            if (deactivatedArguments.equals(arguments))
                result.add(ext);
        }
        return result;
    }

	/**
	 * 
	 * @param bbase argumentation framework
	 * @return model
	 */
    public ArgumentSet getModel(NecessityArgumentationFramework bbase) {
        Collection<Collection<BArgument>> preferredExtensions = new PreferredReasoner().getModels(bbase);
        for(Collection<BArgument> ext: preferredExtensions){
            Set<BArgument> deactivatedArguments = bbase.getDeactivatedArguments(ext);
            Set<BArgument> arguments = new HashSet<>(bbase);
            arguments.removeAll(ext);
            if (deactivatedArguments.equals(arguments))
                return new ArgumentSet(ext);
        }
        return null;
    }

    @Override
    public Collection<Collection<BArgument>> getModels(AbstractBipolarFramework baf) {
        if (baf instanceof NecessityArgumentationFramework) {
            return this.getModels((NecessityArgumentationFramework) baf);
        } else {
            throw new IllegalArgumentException("Unsupported Framework type");
        }
    }
}
