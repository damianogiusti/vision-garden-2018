import com.m17.damianogiusti.vg2018.eval
import com.m17.damianogiusti.vg2018.evalRecursive
import com.m17.damianogiusti.vg2018.getSubExpressions
import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test fun eval() {
        "( + ( * ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 )"
            .let { eval(it) }
            .let { result ->
                assertEquals(60.0, result, 0.0)
            }

        // Can use `÷` as division symbol and `x` as multiplication.
        "( ÷ ( + ( x ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 ) 6 )"
            .let { eval(it) }
            .let { result ->
                assertEquals(10.0, result, 0.0)
            }
    }

    @Test fun evalRecursive() {
        "( + ( * ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 )"
            .let { evalRecursive(it) }
            .let { result ->
                assertEquals(60.0, result, 0.0)
            }

        // Can use `÷` as division symbol and `x` as multiplication.
        "( ÷ ( + ( x ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 ) 6 )"
            .let { evalRecursive(it) }
            .let { result ->
                assertEquals(10.0, result, 0.0)
            }
    }

    @Test fun getSubExpressions() {
        "( + ( * ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 )"
            .let { getSubExpressions(it) }
            .let { (first, second) ->
                assertEquals("( * ( + 5.2 4.8 ) ( - 9.4 4.4 ) )", first)
                assertEquals("10", second)
            }

        "( * ( + 5.2 4.8 ) ( - 9.4 4.4 ) )"
            .let { getSubExpressions(it) }
            .let { (first, second) ->
                assertEquals("( + 5.2 4.8 )", first)
                assertEquals("( - 9.4 4.4 )", second)
            }

        "( + 5.2 4.8 )"
            .let { getSubExpressions(it) }
            .let { (first, second) ->
                assertEquals("5.2", first)
                assertEquals("4.8", second)
            }
    }
}