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
package org.tweetyproject.logics.rpcl.semantics;

import java.util.*;

import org.tweetyproject.commons.*;
import org.tweetyproject.logics.fol.syntax.FolBeliefSet;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;
import org.tweetyproject.logics.commons.syntax.RelationalFormula;
import org.tweetyproject.logics.fol.syntax.Tautology;
import org.tweetyproject.logics.rpcl.syntax.*;
import org.tweetyproject.math.equation.*;
import org.tweetyproject.math.term.*;
import org.tweetyproject.math.probability.*;

/**
 * This class implements aggregating semantics due to [Kern-Isberner, Thimm, KR'2010].
 *
 * @author Matthias Thimm
 *
 */
public class AggregatingSemantics extends AbstractRpclSemantics {

	/** Default Constructor */
	public AggregatingSemantics(){

	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.rpcl.semantics.AbstractRpclSemantics#satisfies(org.tweetyproject.logics.rpcl.semantics.RpclProbabilityDistribution, org.tweetyproject.logics.rpcl.syntax.RelationalProbabilisticConditional)
	 */
	@Override
	public boolean satisfies(RpclProbabilityDistribution<?> p,	RelationalProbabilisticConditional r) {
		if(r.isGround())
			return this.satisfiesGroundConditional(p, r);
		Set<RelationalFormula> groundInstances = r.allGroundInstances(((FolSignature)p.getSignature()).getConstants());
		Double nominator = 0d;
		Double denominator = 0d;
		for(RelationalFormula f: groundInstances){
			FolFormula body = ((RelationalProbabilisticConditional)f).getPremise().iterator().next();
			FolFormula head_and_body;
			if(body instanceof Tautology)
				head_and_body = ((RelationalProbabilisticConditional)f).getConclusion();
			else head_and_body = body.combineWithAnd(((RelationalProbabilisticConditional)f).getConclusion());
			nominator += p.probability(head_and_body).getValue();
			denominator += p.probability(body).getValue();
		}
		return (nominator/denominator) < r.getProbability().getValue() + Probability.PRECISION &&
			(nominator/denominator) > r.getProbability().getValue() - Probability.PRECISION ;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.rpcl.semantics.AbstractRpclSemantics#getSatisfactionStatement(org.tweetyproject.logics.rpcl.syntax.RelationalProbabilisticConditional, org.tweetyproject.logics.fol.syntax.FolSignature, java.util.Map)
	 */
	@Override
	public Statement getSatisfactionStatement(RelationalProbabilisticConditional r, FolSignature signature, Map<Interpretation<FolBeliefSet,FolFormula>,FloatVariable> worlds2vars){
		Set<RelationalFormula> groundInstances = r.allGroundInstances(signature.getConstants());
		if(r.isFact()){
			Term term = null;
			for(RelationalFormula rf: groundInstances){
				RelationalProbabilisticConditional rfg = (RelationalProbabilisticConditional)rf;
				FolFormula head = rfg.getConclusion();
				Term tHead = this.probabilityTerm(head, worlds2vars);
				if(term == null)
					term = tHead;
				else term = term.add(tHead);
			}
			return new Equation(term,new FloatConstant(r.getProbability().getValue() * groundInstances.size()));
		}else{
			Term nominator = null;
			Term denominator = null;
			for(RelationalFormula rf: groundInstances){
				RelationalProbabilisticConditional rfg = (RelationalProbabilisticConditional)rf;
				FolFormula body = rfg.getPremise().iterator().next();
				FolFormula head_and_body = rfg.getConclusion().combineWithAnd(body);
				Term tBody = this.probabilityTerm(body, worlds2vars);
				Term tHead_and_body = this.probabilityTerm(head_and_body, worlds2vars);
				if(nominator == null)
					nominator = tHead_and_body;
				else nominator = nominator.add(tHead_and_body);
				if(denominator == null)
					denominator = tBody;
				else denominator = denominator.add(tBody);
			}
			return new Equation(nominator,denominator.mult(new FloatConstant(r.getProbability().getValue())));
		}
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.relationalprobabilisticconditionallogic.semantics.AbstractRpclSemantics#toString()
	 */
	@Override
	public String toString(){
		return "Aggregating Semantics";
	}

}
