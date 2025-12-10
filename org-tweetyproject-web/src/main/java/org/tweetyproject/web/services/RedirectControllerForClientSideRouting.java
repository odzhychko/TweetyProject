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
 *  Copyright 2025 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
package org.tweetyproject.web.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Configuration load index.html for routes that are handled by client side routing.
 *
 * @author Oleksandr Dzhychko
 */
@Controller
public class RedirectControllerForClientSideRouting {

    // Pattern from https://gedoplan.de/spring-boot-forward-fur-angular-spa/
    // NOTE This pattern is expect to break with Spring Boot 3.x
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String forwardUnmatchedPaths() {
        return "forward:/index.html";
    }
}