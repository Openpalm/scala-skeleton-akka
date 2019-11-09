package playground

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.charset.StandardCharsets

object TestGlobals { 

	val orderLimit = 100000
	val factoryLimit = 1000000000

	def getStream(i: String): ByteArrayInputStream = {
		val bytes = i.getBytes(StandardCharsets.UTF_8)
		new ByteArrayInputStream(bytes)
	}


	val input =
		"""3
	|0 3
	|1 9
	|2 5
	|""".stripMargin

	val input_badOrder =
		s"""3
	| ${orderLimit + 1}
	|1 9
	|""".stripMargin

	val input_badOrderLimit =
		s"""${orderLimit + 1}
	|0 3
	|1 9
	|""".stripMargin

	val input_badFactory =
		s"""3
	|${factoryLimit + 1} 3
	|1 9
	|2 5
	|""".stripMargin

	val GOODORDER =		getStream(input) 
	val BADORDER =		getStream(input_badOrder)
	val BADORDERLIMIT =		getStream(input_badOrderLimit)
	val BADFACTORY =	getStream(input_badFactory)

}
