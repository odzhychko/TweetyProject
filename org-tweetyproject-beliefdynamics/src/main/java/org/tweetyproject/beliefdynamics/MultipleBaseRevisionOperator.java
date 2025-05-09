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
package org.tweetyproject.beliefdynamics;

import java.util.*;

import org.tweetyproject.commons.*;

/**
 * This is the interface for a classic multiple belief base revision operator,
 * ie. an
 * operator that takes some set of formulas and another set of formulas and
 * revises
 * the former by the latter.
 *
 * @author Matthias Thimm
 *
 * @param <T> The type of formulas that this operator works on.
 */
public abstract class MultipleBaseRevisionOperator<T extends Formula> implements BaseRevisionOperator<T> {
	/** Default */
	public MultipleBaseRevisionOperator() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.tweetyproject.beliefdynamics.BaseRevisionOperator#revise(java.util.
	 * Collection, org.tweetyproject.Formula)
	 */
	public Collection<T> revise(Collection<T> base, T formula) {
		Set<T> formulas = new HashSet<T>();
		formulas.add(formula);
		return this.revise(base, formulas);
	}

	/**
	 * Revises the first collection of formulas by the second collection of
	 * formulas.
	 *
	 * @param base     some collection of formulas.
	 * @param formulas some formulas.
	 * @return the revised collection.
	 */
	public abstract Collection<T> revise(Collection<T> base, Collection<T> formulas);

}
