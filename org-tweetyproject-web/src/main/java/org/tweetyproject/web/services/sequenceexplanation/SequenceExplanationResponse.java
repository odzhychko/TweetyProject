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
package org.tweetyproject.web.services.sequenceexplanation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.lang.NonNull;
import org.tweetyproject.web.services.sequenceexplanation.SequenceExplanationService.SequenceExplanations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Response to {@link SequenceExplanationPost}
 *
 * @author Oleksandr Dzhychko
 */
public final class SequenceExplanationResponse {

    public enum Status {
        SUCCESS,
        TIMEOUT,
    }

    private final SequenceExplanationResult reply;
    private final String email;
    private final double time;
    private final String unit_timeout;
    private final Status status;

    public SequenceExplanationResponse(SequenceExplanationResult reply, String email, double time, @NonNull String unit_timeout, Status status) {
        this.reply = reply;
        this.email = email;
        this.time = time;
        this.status = status;
        this.unit_timeout = unit_timeout;
    }

    public SequenceExplanationResult getReply() {
        return reply;
    }

    public String getEmail() {
        return email;
    }

    public double getTime() {
        return time;
    }

    @NonNull
    public String getUnit_timeout() {
        return unit_timeout;
    }

    public Status getStatus() {
        return status;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = GetSequenceExplanationsResult.class, name = "get_sequence_explanations"),
    })
    public interface SequenceExplanationResult {
    }

    public static class GetSequenceExplanationsResult implements SequenceExplanationResult {
        private final Map<String, List<DialectialSequenceExplanationDTO>> perArgumentSequenceExplanations;

        public GetSequenceExplanationsResult(Map<String, List<DialectialSequenceExplanationDTO>> perArgumentSequenceExplanations) {
            this.perArgumentSequenceExplanations = perArgumentSequenceExplanations;
        }

        public Map<String, List<DialectialSequenceExplanationDTO>> getPerArgumentSequenceExplanations() {
            return perArgumentSequenceExplanations;
        }

        public static GetSequenceExplanationsResult from(SequenceExplanations sequenceExplanations) {

            var perArgumentSequenceExplanations = new LinkedHashMap<String, List<DialectialSequenceExplanationDTO>>();
            for (var entry: sequenceExplanations.getPerArgumentSequenceExplanations().entrySet()) {
                var argument = entry.getKey();
                var forArgumentSequenceExplanations = entry.getValue();
                var forArgumentSequenceExplanationDTOs = DialectialSequenceExplanationDTO.from(forArgumentSequenceExplanations);
                perArgumentSequenceExplanations.put(argument.getName(), forArgumentSequenceExplanationDTOs);
            }

            return new GetSequenceExplanationsResult(perArgumentSequenceExplanations);
        }
    }

}
