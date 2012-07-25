package org.scalaide.play2.routeeditor.scanners

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;
import org.scalaide.play2.routeeditor.ColorManager;
import org.scalaide.play2.routeeditor.RouteColorConstants;

class RouteURIScanner(manager: ColorManager) extends AbstractRouteScanner(RouteColorConstants.getToken("ROUTE_URI", manager)) {
  val dynamic = RouteColorConstants.getToken("ROUTE_URI_DYNAMIC",
    manager);

  val rules = Array[IRule](
    // Add a rule for dynamic with colon
    new WordRule(new DynamicColonDetector(), dynamic),
    // Add a rule for dynamic with star
    new WordRule(new DynamicStarDetector(), dynamic),
    // Add a rule for dynamic with dollar
    new WordRule(new DynamicDollarDetector(), dynamic),
    // Add a rule for static
    new WordRule(new StaticDetector(), fDefaultReturnToken))
  setRules(rules);

  private class StaticDetector extends IWordDetector {
    override def isWordStart(c: Char) = {
      c == '/'
    }

    override def isWordPart(c: Char): Boolean = {
      for (w <- " \t\n") {
        if (c == w)
          return false;
      }
      return !(c == ':' || c == '*' || c == '$')
    }

  }

  private abstract class DynamicDetector(start: Char, whiteSpaces: Array[Char]) extends IWordDetector {
    def this(start: Char, whiteSpaces: String) = {
      this(start, whiteSpaces.toCharArray);
    }

    override def isWordStart(c: Char) = {
      c == start
    }

    override def isWordPart(c: Char): Boolean = {
      for (w <- whiteSpaces) {
        if (c == w)
          return false;
      }
      return true;
    }

  }

  private class DynamicColonDetector extends DynamicDetector(':', " \t\n")

  private class DynamicStarDetector extends DynamicDetector('*', " \t\n")

  private class DynamicDollarDetector extends DynamicDetector('$', " \t\n")
}
