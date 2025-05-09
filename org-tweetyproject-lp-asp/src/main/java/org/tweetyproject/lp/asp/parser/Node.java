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
/* Generated By:JJTree: Do not edit this line. Node.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
/* All AST nodes must implement this interface.  It provides basic
   machinery for constructing the parent and child relationships
   between nodes. */
package org.tweetyproject.lp.asp.parser;

/**
 * node interfdace
 */
public interface Node {

	/**
	 * This method is called after the node has been made the current node. It
	 * indicates that child nodes can now be added to it.
	 */
	public void jjtOpen();

	/**
	 * This method is called after all the child nodes have been added.
	 */
	public void jjtClose();

	/**
	 * This pair of methods are used to inform the node of its parent.
	 * @param n node
	 */
	public void jjtSetParent(Node n);

	/**
	 *  Get parent node
	 * @return node
	 */
	public Node jjtGetParent();

	/**
	 * This method tells the node to add its argument to the node's list of
	 * children.
	 * @param n node
	 * @param i index
	 */
	public void jjtAddChild(Node n, int i);

	/**
	 * This method returns a child node. The children are numbered from zero, left
	 * to right.
	 * @param i index
	 * @return a node
	 */
	public Node jjtGetChild(int i);

	/** Return the number of children the node has.
	 * @return num children
	*/
	public int jjtGetNumChildren();

	/**  */
	/**
	 *Accept the visitor.
	 * @param visitor parser visitor
	 * @param data object
	 * @return a new object
	 */
	public Object jjtAccept(ASPParserVisitor visitor, Object data) ;
}
/*
 * JavaCC - OriginalChecksum=0437c0a939b6b91822720f64a2a80969 (do not edit this
 * line)
 */
