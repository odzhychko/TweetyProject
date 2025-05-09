/* Generated By:JavaCC: Do not edit this line. TokenMgrError.java Version 5.0 */
/* JavaCCOptions: */
package org.tweetyproject.lp.asp.parser;

/** Token Manager Error. */
public class TokenMgrError extends Error
{

  /**
   * The version identifier for this Serializable class.
   * Increment only if the <i>serialized</i> form of the
   * class changes.
   */
  private static final long serialVersionUID = 1L;

  /*
   * Ordinals for various reasons why an Error of this type can be thrown.
   */

  /**
   * Lexical error occurred.
   */
  static final int LEXICAL_ERROR = 0;

  /**
   * An attempt was made to create a second instance of a static token manager.
   */
  static final int STATIC_LEXER_ERROR = 1;

  /**
   * Tried to change to an invalid lexical state.
   */
  static final int INVALID_LEXICAL_STATE = 2;

  /**
   * Detected (and bailed out of) an infinite loop in the token manager.
   */
  static final int LOOP_DETECTED = 3;

  /**
   * Indicates the reason why the exception is thrown. It will have
   * one of the above 4 values.
   */
  int errorCode;

  /**
   * Replaces unprintable characters by their escaped (or unicode escaped)
   * equivalents in the given string
   * @param str the string
   * @return escaped string
   */
  protected static final String addEscapes(String str) {
    StringBuffer retval = new StringBuffer();
    char ch;
    for (int i = 0; i < str.length(); i++) {
      switch (str.charAt(i))
      {
        case 0 :
          continue;
        case '\b':
          retval.append("\\b");
          continue;
        case '\t':
          retval.append("\\t");
          continue;
        case '\n':
          retval.append("\\n");
          continue;
        case '\f':
          retval.append("\\f");
          continue;
        case '\r':
          retval.append("\\r");
          continue;
        case '\"':
          retval.append("\\\"");
          continue;
        case '\'':
          retval.append("\\\'");
          continue;
        case '\\':
          retval.append("\\\\");
          continue;
        default:
          if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
            String s = "0000" + Integer.toString(ch, 16);
            retval.append("\\u" + s.substring(s.length() - 4, s.length()));
          } else {
            retval.append(ch);
          }
          continue;
      }
    }
    return retval.toString();
  }

  /**
   * Returns a detailed message for the Error when it is thrown by the
   * token manager to indicate a lexical error.
   * Parameters :
   *    EOFSeen     : indicates if EOF caused the lexical error
   *    curLexState : lexical state in which this error occurred
   *    errorLine   : line number when the error occurred
   *    errorColumn : column number when the error occurred
   *    errorAfter  : prefix that was seen before this error occurred
   *    curchar     : the offending character
   * Note: You can customize the lexical error message by modifying this method.
   */

   /**
 * Generates a detailed error message for a lexical error encountered during parsing.
 * <p>
 * This method constructs a descriptive error message that includes the location of the error
 * (line and column), the character that caused the error, and the text that was parsed immediately
 * before the error occurred. If the end-of-file (EOF) was encountered, it indicates this in the message.
 * </p>
 *
 * @param EOFSeen A boolean indicating if the end-of-file (EOF) was encountered during the error.
 * @param lexState The lexical state of the parser when the error occurred.
 * @param errorLine The line number where the error occurred.
 * @param errorColumn The column number where the error occurred.
 * @param errorAfter The text that was parsed immediately before the error occurred.
 * @param curChar The character that caused the error.
 * @return A string describing the lexical error, including its location and the problematic character.
 */
  protected static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
    return("Lexical error at line " +
          errorLine + ", column " +
          errorColumn + ".  Encountered: " +
          (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"") + " (" + (int)curChar + "), ") +
          "after : \"" + addEscapes(errorAfter) + "\"");
  }

  /**
   * You can also modify the body of this method to customize your error messages.
   * For example, cases like LOOP_DETECTED and INVALID_LEXICAL_STATE are not
   * of end-users concern, so you can return something like :
   *
   *     "Internal Error : Please file a bug report .... "
   *
   * from this method for such cases in the release version of your parser.
   *
   * @return the message
   */
  public String getMessage() {
    return super.getMessage();
  }

  /*
   * Constructors of various flavors follow.
   */

  /** No arg constructor. */
  public TokenMgrError() {
  }

/**
 * Constructs a new {@code TokenMgrError} with a specified error message and reason code.
 * <p>
 * This constructor initializes the error with a descriptive message and an integer code that
 * represents the specific reason for the error. The message typically describes what went wrong,
 * while the reason code can be used to identify the type of error.
 * </p>
 *
 * @param message A description of the error.
 * @param reason An integer code representing the type of error.
 */
public TokenMgrError(String message, int reason) {
  super(message);
  errorCode = reason;
}

/**
* Constructs a new {@code TokenMgrError} with detailed information about a lexical error.
* <p>
* This constructor generates an error message based on the context in which the error occurred,
* including whether the end-of-file was encountered, the current lexical state, the position in the
* input (line and column), the text immediately following the error, and the character that caused
* the error. It also includes a reason code for further categorization of the error.
* </p>
*
* @param EOFSeen A boolean indicating if the end-of-file was encountered during the error.
* @param lexState The lexical state of the parser when the error occurred.
* @param errorLine The line number where the error occurred.
* @param errorColumn The column number where the error occurred.
* @param errorAfter The text that was parsed immediately before the error occurred.
* @param curChar The character that caused the error.
* @param reason An integer code representing the type of error.
*/
public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
  this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
}

}
/* JavaCC - OriginalChecksum=ef323cc62905792270b5e2468716dae9 (do not edit this line) */
