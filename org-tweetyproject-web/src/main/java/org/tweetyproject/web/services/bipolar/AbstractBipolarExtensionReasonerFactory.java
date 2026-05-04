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
 *  Copyright 2026 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.web.services.bipolar;

import org.tweetyproject.arg.bipolar.reasoner.*;
import org.tweetyproject.arg.bipolar.reasoner.deductive.*;
import org.tweetyproject.arg.bipolar.reasoner.necessity.*;

/**
 * Main factory for retrieving bipolar extension reasoners.
 * 
 * @author Lars Bengel
 */
public abstract class AbstractBipolarExtensionReasonerFactory {

	/** An enumeration of all available semantics. */
	public enum Semantics {
		/** General Semantics */
		CF("cf", "Conflict-Free"),
		SA("sa", "Safe"),
		CL("cl", "Closed"),
		/** Semantics for Deductive Interpretation */
		CAD("c-ad", "c-Admissible"),
		DAD("d-ad", "d-Admissible"),
		/** Semantics for Necessary Interpretation */
		NAD("n-ad", "Admissible"),
		NCO("n-co", "Complete"),
		NGR("n-gr", "Grounded"),
		NPR("n-pr", "Preferred"),
		NST("n-st", "Stable");

		/** id */
		public String id;
		/** label */
		public String label;

		Semantics(String id, String label) {
			this.id = id;
			this.label = label;
		}

		/**
		 *
		 * @param id ID
		 * @return the semantics
		 */
		public static Semantics getSemantics(String id) {
			for (Semantics m : Semantics.values())
				if (m.id.equals(id))
					return m;
			return null;
		}
	}

	/**
	 * Returns an array of all available semantics.
	 *
	 * @return An array of all available semantics.
	 */
	public static Semantics[] getSemantics() {
		return Semantics.values();
	}

	/**
	 * Creates a new reasoner measure of the given semantics with default
	 * settings.
	 * 
	 * @param sem some identifier of an semantics.
	 * @return the requested reasoner.
	 */
	public static AbstractBipolarExtensionReasoner getReasoner(Semantics sem) {
        return switch (sem) {
            case CF -> new ConflictFreeReasoner();
			case SA -> new SafetyReasoner();
			case CL -> new ClosureReasoner();
			case CAD -> new CAdmissibleReasoner();
			case DAD -> new DAdmissibleReasoner();
			case NAD -> new AdmissibleReasoner();
			case NCO -> new CompleteReasoner();
			case NGR -> new GroundedReasoner();
			case NPR -> new PreferredReasoner();
			case NST -> new StableReasoner();
            default -> throw new RuntimeException("No reasoner found for semantics " + sem.toString());
        };
	}
}
