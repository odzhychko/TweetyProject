package org.tweetyproject.causal.reasoner;

import org.junit.jupiter.api.Test;
import org.tweetyproject.causal.syntax.CausalKnowledgeBase;
import org.tweetyproject.causal.syntax.StructuralCausalModel;
import org.tweetyproject.logics.pl.syntax.Equivalence;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.Proposition;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentationBasedCausalReasonerTest {

    @Test
    public void example1() throws Throwable {
        Proposition corona = new Proposition("corona");
        Proposition influenza = new Proposition("influenza");
        Proposition atRisk = new Proposition("at-risk");
        Proposition covid = new Proposition("covid");
        Proposition flu = new Proposition("flu");
        Proposition shortOfBreath = new Proposition("short-of-breath");
        Proposition fever = new Proposition("fever");
        Proposition chills = new Proposition("chills");
        Collection<PlFormula> modelEquations = List.of(
                new Equivalence(covid, corona),
                new Equivalence(flu, influenza),
                new Equivalence(fever, covid.combineWithOr(flu)),
                new Equivalence(chills, fever),
                new Equivalence(shortOfBreath, covid.combineWithAnd(atRisk))
        );
        StructuralCausalModel model = new StructuralCausalModel(modelEquations);
        List<PlFormula> assumptions = List.of(atRisk, corona, new Negation(influenza));
        CausalKnowledgeBase knowledgeBase = new CausalKnowledgeBase(model, assumptions);
        Collection<PlFormula> observations = List.of(covid);
        ArgumentationBasedCausalReasoner reasoner = new ArgumentationBasedCausalReasoner();

        var perAtomInfluencingAtoms = reasoner.getSignificantAtoms(knowledgeBase, observations, Map.of());

        Map<PlFormula, Collection<Proposition>> perConclusionExpectedInfluencingAtoms = Map.of(
                atRisk, Set.of(atRisk),
                corona, Set.of(corona, covid),
                influenza, Set.of(influenza),
                covid, Set.of(covid),
                shortOfBreath, Set.of(atRisk, shortOfBreath, covid),
                fever, Set.of(fever, flu, covid),
                chills, Set.of(covid, fever, chills, flu),
                flu, Set.of(influenza, flu)
        );

        assertEquals(perConclusionExpectedInfluencingAtoms, perAtomInfluencingAtoms);
    }
}