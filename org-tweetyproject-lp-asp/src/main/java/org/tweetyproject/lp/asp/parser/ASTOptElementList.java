/* Generated By:JJTree&JavaCC: Do not edit this line. ASPParserTokenManager.java */
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
 *  Copyright 2018 The TweetyProject Team <http://tweetyproject.org/contact/>
 */
/* Generated By:JJTree: Do not edit this line. ASTOptElementList.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.tweetyproject.lp.asp.parser;

/**
 * Represents a node in the abstract syntax tree (AST) for an optional element list in the ASP (Answer Set Programming) parser.
 * This class extends {@link SimpleNode} and provides methods for accepting a visitor.
 */
public class ASTOptElementList extends SimpleNode {

    /**
     * Constructs a new {@code ASTOptElementList} with the specified node ID.
     *
     * @param id The node ID.
     */
    public ASTOptElementList(int id) {
        super(id);
    }

    /**
     * Constructs a new {@code ASTOptElementList} with the specified parser and node ID.
     *
     * @param p The parser instance used to create this node.
     * @param id The node ID.
     */
    public ASTOptElementList(ASPParser p, int id) {
        super(p, id);
    }

    /**
     * Accepts a visitor and allows it to visit this node.
     *
     * @param visitor The visitor to accept.
     * @param data Optional data to pass to the visitor.
     * @return The result of the visitor's visit method.
     */
    @Override
    public Object jjtAccept(ASPParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}

/*
 * JavaCC - OriginalChecksum=e4534876b2131fca40fd082fc27fa69d (do not edit this
 * line)
 */
