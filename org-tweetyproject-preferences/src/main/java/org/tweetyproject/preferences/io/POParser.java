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
/* Generated By:JavaCC: Do not edit this line. POParser.java */
package org.tweetyproject.preferences.io;

import java.io.File;
import java.util.*;

import org.tweetyproject.commons.util.Triple;
import org.tweetyproject.preferences.*;

/**
 * POParser class
 *
 */
@SuppressWarnings("all")
public class POParser implements POParserConstants {
  /** Constructor */
  public POParser() {
  }

  /**
   * Parses a {@link PreferenceOrder} of type {@link String} from a given
   * {@link File}.
   * <p>
   * This method creates a {@link POParser} instance and parses the file to
   * generate a
   * {@link PreferenceOrder<String>}. The file must contain the data in a format
   * understood
   * by the parser.
   *
   * @param file The file containing the preference order data to be parsed.
   * @return A {@link PreferenceOrder<String>} parsed from the specified file.
   * @throws ParseException                If there is an error during parsing.
   * @throws java.io.FileNotFoundException If the specified file is not found.
   */
  public static PreferenceOrder<String> parse(File file) throws ParseException, java.io.FileNotFoundException {
    POParser parser;
    parser = new POParser(new java.io.FileInputStream(file));
    return parser.StringPreferenceOrder();
  }

  /**
   * Parses a {@link PreferenceOrder} of type {@link String} from a given file
   * path.
   * <p>
   * This method creates a {@link POParser} instance and parses the file at the
   * specified
   * file path to generate a {@link PreferenceOrder<String>}. The file must
   * contain the data
   * in a format understood by the parser.
   *
   * @param filename The file path of the preference order data to be parsed.
   * @return A {@link PreferenceOrder<String>} parsed from the file at the
   *         specified path.
   * @throws ParseException                If there is an error during parsing.
   * @throws java.io.FileNotFoundException If the file at the specified path is
   *                                       not found.
   */
  public static PreferenceOrder<String> parse(String filename) throws ParseException, java.io.FileNotFoundException {
    POParser parser;
    parser = new POParser(new java.io.FileInputStream(filename));
    return parser.StringPreferenceOrder();
  }

  /**
   * Test
   *
   * @param args the args
   */
  public static void main(String args[]) {
    try {
      PreferenceOrder<String> TestPO = new PreferenceOrder<String>();
      // Error with reading in file, path has to be edited manually
      TestPO = parse("test.po");
      System.out.println(TestPO);
      System.out.println(TestPO.getDomainElements());
      TestPO.addPair("g", "h", Relation.LESS);
      System.out.println(TestPO.getDomainElements());
      System.out.println(TestPO);
    } catch (Exception e) {
      System.out.println("error while parsing: " + e);
    }
  }

  /**
   *  Get a StringPreferenceOrder
   * @return a PreferenceOrder
   * @throws ParseException error
   */
  final public PreferenceOrder<String> StringPreferenceOrder() throws ParseException {
    Token t;
    Token t0, t1, t2;
    Set<String> singleElements = new HashSet<String>();
    Set<Triple<String, String, Relation>> entries = new HashSet<Triple<String, String, Relation>>();
    jj_consume_token(LBRA);
    label_1: while (true) {
      t = jj_consume_token(ELEMENT);
      singleElements.add(t.image);
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case 7:
          jj_consume_token(7);
          break;
        default:
          jj_la1[0] = jj_gen;
          ;
      }
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case ELEMENT:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_1;
      }
    }
    jj_consume_token(RBRA);
    jj_consume_token(EOL);
    label_2: while (true) {
      t0 = jj_consume_token(ELEMENT);
      t2 = jj_consume_token(REL);
      t1 = jj_consume_token(ELEMENT);
      if (t2.image.equals("<=")) {
        Triple<String, String, Relation> ent = new Triple<String, String, Relation>(t0.image, t1.image,
            Relation.LESS_EQUAL);
        entries.add(ent);
      } else if (t2.image.equals("<")) {
        Triple<String, String, Relation> ent = new Triple<String, String, Relation>(t0.image, t1.image, Relation.LESS);
        entries.add(ent);
      } else {
        continue;
      }
      jj_consume_token(EOL);
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case ELEMENT:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
      }
    }
    PreferenceOrder<String> n = new PreferenceOrder<String>(entries);
    {
      if (true)
        return n;
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public POParserTokenManager token_source;
  /** jj input stream */
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  /** jj_ntk */
  private int jj_ntk;
  /** jj_gen */
  private int jj_gen;
  /** jj_la1 */
  final private int[] jj_la1 = new int[3];
  /** jj_la1_0 */
  static private int[] jj_la1_0;
  static {
    jj_la1_init_0();
  }

  private static void jj_la1_init_0() {
    jj_la1_0 = new int[] { 0x80, 0x10, 0x10, };
  }

  /**
   * Constructor with InputStream.
   *
   * @param stream inputstream
   */
  public POParser(java.io.InputStream stream) {
    this(stream, null);
  }

  /**
   * Constructor with InputStream and supplied encoding
   *
   * @param stream   inputstream
   * @param encoding the encoding
   */
  public POParser(java.io.InputStream stream, String encoding) {
    try {
      jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
    } catch (java.io.UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    token_source = new POParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  /**
 * Reinitializes the parser with a new {@link java.io.InputStream}.
 * <p>
 * This method reinitializes the input stream with default encoding.
 * The internal state of the parser is reset, preparing it for parsing new data from the provided {@link java.io.InputStream}.
 *
 * @param stream The new {@link java.io.InputStream} to initialize the parser with.
 */
  public void ReInit(java.io.InputStream stream) {
    ReInit(stream, null);
  }

  /**
 * Reinitializes the parser with a new {@link java.io.InputStream} and a specified encoding.
 * <p>
 * This method reinitializes the input stream with the provided encoding, resetting the internal state of the parser
 * to allow for parsing new data from the provided {@link java.io.InputStream}.
 *
 * @param stream   The new {@link java.io.InputStream} to initialize the parser with.
 * @param encoding The encoding to use for the input stream. If the encoding is unsupported, a {@link RuntimeException} will be thrown.
 * @throws RuntimeException If the provided encoding is unsupported.
 */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try {
      jj_input_stream.ReInit(stream, encoding, 1, 1);
    } catch (java.io.UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  /**
   * Constructor with reader stream.
   *
   * @param stream reader
   */
  public POParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new POParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  /**
 * Reinitializes the parser with a new {@link java.io.Reader}.
 * <p>
 * This method reinitializes the input stream using a {@link java.io.Reader} and resets the internal state of the parser,
 * allowing for parsing new data from the provided {@link java.io.Reader}.
 *
 * @param stream The new {@link java.io.Reader} to initialize the parser with.
 */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  /**
   * Constructor with generated Token Manager.
   *
   * @param tm token manager
   */
  public POParser(POParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  /**
 * Reinitializes the parser with a new {@link POParserTokenManager}.
 * <p>
 * This method reinitializes the token manager used by the parser, resetting the internal state of the parser
 * to prepare it for parsing new data using the provided {@link POParserTokenManager}.
 *
 * @param tm The new {@link POParserTokenManager} to initialize the parser with.
 */
  public void ReInit(POParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++)
      jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null)
      token = token.next;
    else
      token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  /**
   * Retrieves the next {@link Token} from the input stream.
   * <p>
   * This method advances the token pointer to the next token in the input stream.
   * If the next token has already been fetched, it is used directly. Otherwise,
   * the method requests the next token from the token source.
   * <p>
   * Additionally, it updates the state of the parser, including resetting
   * the lookahead token kind (`jj_ntk`) and incrementing the generation counter
   * (`jj_gen`).
   *
   * @return the next {@link Token} in the input stream.
   */
  final public Token getNextToken() {
    if (token.next != null)
      token = token.next;
    else
      token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  /**
   * Retrieves the {@link Token} at a specific position relative to the current
   * token.
   * <p>
   * This method iterates over the token stream starting from the current token
   * and advances
   * until it reaches the specified index. If the required token has already been
   * fetched,
   * it will simply use it. Otherwise, it will request tokens from the token
   * source until the
   * desired token is reached.
   *
   * @param index The number of tokens forward from the current token to retrieve.
   *              For example, passing 0 will return the current token, passing 1
   *              will return
   *              the next token, and so on.
   * @return The {@link Token} at the specified position relative to the current
   *         token.
   */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null)
        t = t.next;
      else
        t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt = token.next) == null)
      return (jj_ntk = (token.next = token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /**
   * Generates a {@link ParseException} that describes the parsing error that
   * occurred.
   * <p>
   * This method constructs a {@link ParseException} object that includes details
   * about
   * the parsing error, such as the token that caused the error, expected token
   * sequences,
   * and the token image for displaying informative error messages.
   * <p>
   * It collects information about the tokens that were expected but not found,
   * and uses this information to construct a detailed exception.
   *
   * @return a {@link ParseException} object that contains information about the
   *         parsing error.
   */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[8];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 3; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1 << j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 8; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
