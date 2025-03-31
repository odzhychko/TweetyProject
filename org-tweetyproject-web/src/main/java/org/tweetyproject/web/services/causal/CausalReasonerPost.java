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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * Request to execute a {@link CausalReasonerPost#cmd} with a {@link org.tweetyproject.causal.reasoner.AbstractCausalReasoner}
 *
 * @author Oleksandr Dzhychko
 */
public class CausalReasonerPost {

    public CausalReasonerPost(
            @JsonProperty(value = "cmd", required = true)
            @NonNull Cmd cmd,
            @JsonProperty("email")
            String email,
            @JsonProperty(value = "kb", required = true)
            @NonNull String kb,
            @JsonProperty(value = "observations", required = true)
            @NonNull String observations,
            @JsonProperty(value = "timeout", required = true)
            int timeout, @NonNull
            @JsonProperty(value = "unit_timeout", required = true)
            String unit_timeout
    ) {
        this.cmd = cmd;
        this.email = email;
        this.kb = kb;
        this.observations = observations;
        this.timeout = timeout;
        this.unit_timeout = unit_timeout;
    }

    /**
     * Describes which command should be executed by the causal reasoner
     */
    public enum Cmd {
        /**
         * Instructs the reasoner to calculate the conclusions
         *
         * @see org.tweetyproject.causal.reasoner.AbstractCausalReasoner#getConclusions
         */
        @JsonProperty("get_conclusions")
        GET_CONCLUSIONS
    }

    /**
     * The command type for the reasoner request
     */
    @NonNull
    private Cmd cmd;

    /**
     * The email associated with the request
     */
    private String email;

    /**
     * The knowledge base (KB) for the reasoner request
     * The format of the knowledge base must be as described be {@link org.tweetyproject.causal.parser.CausalParser}.
     */
    @NonNull
    private String kb;

    /**
     * The observations for the reasoner request
     * The format of the knowledge base must be as described be {@link org.tweetyproject.causal.parser.CausalParser}.
     */
    @NonNull
    private String observations;

    /**
     * The timeout in seconds for the reasoner request
     */
    private int timeout;

    /**
     * The unit timeout for the reasoner request
     */
    @NonNull
    private String unit_timeout;

    public Cmd getCmd() {
        return this.cmd;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKb() {
        return this.kb;
    }

    public void setKb(String kb) {
        this.kb = kb;
    }

    public String getObservations() {
        return this.observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUnit_timeout() {
        return this.unit_timeout;
    }

    public void setUnit_timeout(String unit_timeout) {
        this.unit_timeout = unit_timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CausalReasonerPost)) {
            return false;
        }
        CausalReasonerPost CausalReasonerPost = (CausalReasonerPost) o;
        return Objects.equals(cmd, CausalReasonerPost.cmd) && Objects.equals(email, CausalReasonerPost.email) && Objects.equals(kb, CausalReasonerPost.kb) && Objects.equals(observations, CausalReasonerPost.observations) && timeout == CausalReasonerPost.timeout && Objects.equals(unit_timeout, CausalReasonerPost.unit_timeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmd, email, kb, observations, timeout, unit_timeout);
    }

    @Override
    public String toString() {
        return "{" + " cmd='" + getCmd() + "'" + ", email='" + getEmail() + "'" + ", kb='" + getKb() + "'" + ", query_assumption='" + getObservations() + "'" + ", timeout='" + getTimeout() + "'" + ", unit_timeout='" + getUnit_timeout() + "'" + "}";
    }
}