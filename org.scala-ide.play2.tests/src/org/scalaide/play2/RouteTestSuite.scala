package org.scalaide.play2


import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Assert._
import org.junit.runners.Suite.SuiteClasses
import org.scalaide.play2.routeeditor.RouteHyperlinkDetectorTest
import org.scalaide.play2.routeeditor.scanners.RouteActionScannerTest
import org.scalaide.play2.routeeditor.scanners.RouteURIScannerTest
import org.scalaide.play2.routeeditor.scanners.RouteScannerTest
import org.scalaide.play2.routeeditor.tools.MethodFinderTest

@RunWith(value=classOf[org.junit.runners.Suite])
@SuiteClasses(value=Array(
    classOf[RouteHyperlinkDetectorTest],
    classOf[RouteActionScannerTest],
    classOf[RouteURIScannerTest],
    classOf[RouteScannerTest],
    classOf[MethodFinderTest]))
class RouteTestSuite {
	
}
