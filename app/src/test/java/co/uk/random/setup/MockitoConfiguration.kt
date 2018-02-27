package co.uk.random.setup

import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.configuration.DefaultMockitoConfiguration
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer


class MockitoConfiguration : DefaultMockitoConfiguration() {

    /**
     * Disables the Mockito cache to prevent Mockito & Robolectric bugs.
     */

    override fun enableClassCache(): Boolean {
        return false
    }

    /**
     * change default value returned in a method invocation
     */

    override fun getDefaultAnswer(): Answer<Any> {
        return object : ReturnsEmptyValues() {
            override fun answer(inv: InvocationOnMock): Any {
                val type = inv.method.returnType
                return if (type.isAssignableFrom(Observable::class.java)) {
                    Observable.error<Any>(createException(inv))
                } else if (type.isAssignableFrom(Single::class.java)) {
                    Single.error<Any>(createException(inv))
                } else {
                    super.answer(inv)
                }
            }
        }
    }

    private fun createException(
            invocation: InvocationOnMock): RuntimeException {
        val s = invocation.toString()
        return RuntimeException("No mock defined for invocation " + s)
    }
}
