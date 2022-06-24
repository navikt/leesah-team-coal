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
        if (question.category == "make-ingress") handleIngress(question)
        if (question.category == "NAV") handleNAV(question)
        if (question.category == "deduplication") handleDeduplication(question)
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

    private fun handleIngress(question: Question) {
        val res = "https://leesah-team-coal.dev.intern.nav.no"
        answer(question.category, question.id(), res)
    }

    private fun handleNAV(question: Question) {
        var spm = when (question.question) {
            "På hvilken nettside finner man informasjon om rekruttering til NAV IT?" -> "detsombetyrnoe.no"
            "Hva heter applikasjonsplattformen til NAV?" -> "Nais"
            else -> ""
        }
        answer(question.category, question.id(), spm)
    }

    private fun handleDeduplication(question: Question) {
        database.

    }

    /**
     * Parse and calculate the string passed in param
     *
     * @param calculation
     * @return result of the calculation
     */
    fun parseAndCalculation(calculation: String): Int {
        val symbols = calculation.split(" ")
        val sym1 = symbols[0].toInt()
        val sym2 = symbols[2].toInt()
        return when (symbols[1]) {
            "+" -> sym1 + sym2
            "-" -> sym1 - sym2
            "*" -> sym1 * sym2
            "/" -> (sym1 / sym2)
            else -> 0
        }

    }
}