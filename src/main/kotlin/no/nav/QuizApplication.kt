package no.nav

import no.nav.db.Database
import no.nav.quizrapid.*
import no.nav.rapid.Assessment
import no.nav.rapid.Question


/**
 * QuizApplication
 *
 * Her skal teamet bygge ut funksjonalitet for å løse oppgavene i leesah-game.
 */
class QuizApplication(private val teamName: String, database: Database? = null): QuizParticipant(teamName) {

    override fun handle(question: Question) {
        logger.log(question)
        if (question.category == "team-registration") handleRegisterTeam(question)
        if (question.category == "arithmetic") handleMath(question)
    }


    override fun handle(assessment: Assessment) {
        logger.log(assessment)
    }

    /**
     * Spørsmål handlers
     */

    private fun handleRegisterTeam(question: Question) {
        answer(question.category, question.id(), teamName)
    }

    private fun handleMath(question: Question) {
        val result = parseAndCalculation(question.question)
        answer(question.category, question.id(), result.toString())


    }

    /**
     * Parse and calculate the string passed in param
     *
     * @param calculation
     * @return result of the calculation
     */
    fun parseAndCalculation(calculation: String): Int {
        var result = 0

        var multiplier = 1
        var subProblem = -1
        val symbols = calculation.split(" ")

        for ((index, c) in symbols.withIndex()) {
            if(subProblem == -1 || c != ")")
                when(c) {
                    "+" -> multiplier = 1
                    "-" -> multiplier = -1
                    "(" -> subProblem = index
                    ")" -> {
                        result += multiplier * parseAndCalculation(calculation.substring(subProblem + 1, index))
                        subProblem = -1
                    }
                    else -> result += multiplier * c.toInt()
                }
        }

        return result
    }
}