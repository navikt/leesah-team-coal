package no.nav

import no.nav.rapid.Answer
import no.nav.rapid.Question
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuizApplicationTest {


    @Test
    fun `håndterer team-registration`() {
        val teamNavn = "mitt-team-navn"
        val qa = QuizApplication(teamNavn)
        qa.handle(Question(category = "team-registration", question =  "register new team..."))
        val messages = qa.messages()
        assertEquals(1, messages.size)
        assertEquals(teamNavn, (messages[0] as Answer).answer)
    }

    @Test
    fun testMathPlus() {
        val infix = "34 + 22"
        val qa = QuizApplication("coal")
        qa.handle(Question(category = "arithmetic", question =  infix))
        val messages = qa.messages()
        assertEquals(1, messages.size)
        assertEquals("56", (messages[0] as Answer).answer)
    }
    @Test
    fun testMathMult() {
        val infix = "10 * 2"
        val qa = QuizApplication("coal")
        qa.handle(Question(category = "arithmetic", question =  infix))
        val messages = qa.messages()
        assertEquals(1, messages.size)
        assertEquals("20", (messages[0] as Answer).answer)
    }


    @Test
    fun testMathMinus() {
        val infix = "34 - 22"
        val qa = QuizApplication("coal")
        qa.handle(Question(category = "arithmetic", question =  infix))
        val messages = qa.messages()
        assertEquals(1, messages.size)
        assertEquals("12", (messages[0] as Answer).answer)
    }

    @Test
    fun testMathDivide() {
        val infix = "9 / 3"
        val qa = QuizApplication("coal")
        qa.handle(Question(category = "arithmetic", question =  infix))
        val messages = qa.messages()
        assertEquals(1, messages.size)
        assertEquals("3", (messages[0] as Answer).answer)
    }
}