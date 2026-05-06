package org.tweetyproject.arg.bipolar.reasoner;

import org.tweetyproject.arg.bipolar.syntax.AbstractBipolarFramework;
import org.tweetyproject.arg.bipolar.syntax.BArgument;
import org.tweetyproject.commons.InferenceMode;

import java.util.Collection;

/**
 * Abstract Reasoner for Bipolar Argumentation
 *
 * @author Lars Bengel
 */
public abstract class AbstractBipolarExtensionReasoner  {
    /**
     * Compute all extensions of this semantics
     * @param baf some bipolar AF
     * @return the set of extensions
     */
    public abstract Collection<Collection<BArgument>> getModels(AbstractBipolarFramework baf);

    /**
     * Compute a single extension of this semantics
     * @param baf some bipolar AF
     * @return some extension
     */
    public Collection<BArgument> getModel(AbstractBipolarFramework baf) {
        return getModels(baf).iterator().next();
    }

    /**
     * Queries the given BAF for the given argument using the given
     * skeptical inference.
     * @param beliefbase a BAF
     * @param formula a single argument
     * @return "true" if the argument is accepted
     */
    public Boolean query(AbstractBipolarFramework beliefbase, BArgument formula) {
        return this.query(beliefbase, formula, InferenceMode.SKEPTICAL);
    }

    /**
     * Queries the given BAF for the given argument using the given
     * inference type.
     * @param beliefbase a BAF
     * @param formula a single argument
     * @param inferenceMode either InferenceMode.SKEPTICAL or InferenceMode.CREDULOUS
     * @return "true" if the argument is accepted
     */
    public Boolean query(AbstractBipolarFramework beliefbase, BArgument formula, InferenceMode inferenceMode) {
        Collection<Collection<BArgument>> extensions = this.getModels(beliefbase);
        if(inferenceMode.equals(InferenceMode.SKEPTICAL)){
            for(Collection<BArgument> e: extensions)
                if(!e.contains(formula))
                    return false;
            return true;
        }
        // so its credulous semantics
        for(Collection<BArgument> e: extensions){
            if(e.contains(formula))
                return true;
        }
        return false;
    }
}
