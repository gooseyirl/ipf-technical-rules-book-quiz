package com.ipf.technicalrulesquiz.data.repository

import com.ipf.technicalrulesquiz.data.model.QuizQuestion
import com.ipf.technicalrulesquiz.data.model.RuleReference

class QuizRepository {

    fun getAllQuestions(): List<QuizQuestion> {
        return sampleQuestions
    }

    fun getRandomQuestions(count: Int): List<QuizQuestion> {
        return sampleQuestions.shuffled().take(count)
    }

    fun getQuestionsBySection(section: String): List<QuizQuestion> {
        return sampleQuestions.filter { it.ruleReference.section == section }
    }

    companion object {
        // Sample questions - These should be replaced with actual IPF Technical Rules
        // You can expand this list by parsing the PDF or manually adding questions
        private val sampleQuestions = listOf(
            QuizQuestion(
                id = 1,
                question = "What is the maximum allowable diameter for a powerlifting bar in competition?",
                options = listOf("28mm", "29mm", "30mm", "32mm"),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Equipment Specifications",
                    subsection = "The Bar",
                    ruleNumber = "1.1",
                    pageNumber = 10
                ),
                explanation = "The bar must have a diameter of 29mm with a tolerance of ±0.5mm."
            ),
            QuizQuestion(
                id = 2,
                question = "What is the minimum depth a lifter must achieve in the squat?",
                options = listOf(
                    "Hips level with knees",
                    "Hips below the top of the knees",
                    "Thighs parallel to the ground",
                    "90-degree angle at the knee"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Rules of Performance",
                    subsection = "Squat",
                    ruleNumber = "2.3.4",
                    pageNumber = 15
                ),
                explanation = "The top surface of the legs at the hip joint must be lower than the top of the knees."
            ),
            QuizQuestion(
                id = 3,
                question = "How many attempts is each lifter allowed for each lift in competition?",
                options = listOf("2", "3", "4", "5"),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Rules of Performance",
                    subsection = "General",
                    ruleNumber = "2.1",
                    pageNumber = 12
                ),
                explanation = "Each lifter is allowed three attempts at each lift."
            ),
            QuizQuestion(
                id = 4,
                question = "What color lights indicate a successful lift?",
                options = listOf("Green", "White", "Red", "Yellow"),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Judging",
                    subsection = "Referee Signals",
                    ruleNumber = "3.2",
                    pageNumber = 20
                ),
                explanation = "White lights indicate a good lift. Red lights indicate no lift."
            ),
            QuizQuestion(
                id = 5,
                question = "In the bench press, what must touch the chest?",
                options = listOf(
                    "The center of the bar",
                    "The entire bar equally",
                    "Any part of the bar",
                    "The bar must not touch the chest"
                ),
                correctAnswerIndex = 0,
                ruleReference = RuleReference(
                    section = "Rules of Performance",
                    subsection = "Bench Press",
                    ruleNumber = "2.4.3",
                    pageNumber = 16
                ),
                explanation = "The bar must be lowered to the chest and held motionless on the chest with a definite and visible pause."
            ),
            QuizQuestion(
                id = 6,
                question = "What is the maximum weight of the heaviest disc plate used in IPF competitions?",
                options = listOf("20kg", "25kg", "30kg", "50kg"),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Equipment Specifications",
                    subsection = "Discs",
                    ruleNumber = "1.2",
                    pageNumber = 11
                ),
                explanation = "Discs shall be 25kg or less."
            ),
            QuizQuestion(
                id = 7,
                question = "In the deadlift, when can the lifter lower the bar?",
                options = listOf(
                    "Immediately after lockout",
                    "After the 'Down' signal from the referee",
                    "When the bar stops moving upward",
                    "Any time after passing the knees"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Rules of Performance",
                    subsection = "Deadlift",
                    ruleNumber = "2.5.4",
                    pageNumber = 18
                ),
                explanation = "The lifter must wait for the referee's 'Down' signal before lowering the bar."
            ),
            QuizQuestion(
                id = 8,
                question = "What is the minimum weight of the lightest change plate?",
                options = listOf("0.25kg", "0.5kg", "1.0kg", "1.25kg"),
                correctAnswerIndex = 0,
                ruleReference = RuleReference(
                    section = "Equipment Specifications",
                    subsection = "Small Discs",
                    ruleNumber = "1.2.3",
                    pageNumber = 11
                ),
                explanation = "Small discs of 0.25kg must be available for record attempts."
            ),
            QuizQuestion(
                id = 9,
                question = "How many referees officiate each lift?",
                options = listOf("1", "2", "3", "4"),
                correctAnswerIndex = 2,
                ruleReference = RuleReference(
                    section = "Judging",
                    subsection = "Referee Composition",
                    ruleNumber = "3.1",
                    pageNumber = 19
                ),
                explanation = "Three referees shall be present to judge each lift."
            ),
            QuizQuestion(
                id = 10,
                question = "What is required for a lift to be considered successful?",
                options = listOf(
                    "All three white lights",
                    "At least two white lights",
                    "Majority white lights",
                    "Chief referee approval"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Judging",
                    subsection = "Lift Decision",
                    ruleNumber = "3.3",
                    pageNumber = 20
                ),
                explanation = "A majority decision (at least two white lights) is required for a successful lift."
            ),
            QuizQuestion(
                id = 11,
                question = "What must a lifter wear during competition?",
                options = listOf(
                    "Any athletic clothing",
                    "Approved singlet or one-piece suit",
                    "Shorts and t-shirt",
                    "Team uniform"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Personal Equipment",
                    subsection = "Costume",
                    ruleNumber = "4.1",
                    pageNumber = 22
                ),
                explanation = "A one-piece suit (singlet) conforming to IPF specifications must be worn."
            ),
            QuizQuestion(
                id = 12,
                question = "Is a lifting belt mandatory in IPF competitions?",
                options = listOf(
                    "Yes, always required",
                    "No, it is optional",
                    "Only for deadlift",
                    "Only for squat"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Personal Equipment",
                    subsection = "Belt",
                    ruleNumber = "4.2",
                    pageNumber = 23
                ),
                explanation = "A belt is optional but if worn, it must conform to IPF specifications."
            ),
            QuizQuestion(
                id = 13,
                question = "What is the maximum thickness of a lifting belt?",
                options = listOf("10mm", "12mm", "13mm", "15mm"),
                correctAnswerIndex = 2,
                ruleReference = RuleReference(
                    section = "Personal Equipment",
                    subsection = "Belt Specifications",
                    ruleNumber = "4.2.1",
                    pageNumber = 23
                ),
                explanation = "The belt maximum thickness is 13mm."
            ),
            QuizQuestion(
                id = 14,
                question = "Can a lifter use knee wraps in equipped competitions?",
                options = listOf(
                    "Yes, always",
                    "Yes, but only in equipped division",
                    "No, never",
                    "Only with special permission"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Personal Equipment",
                    subsection = "Knee Wraps",
                    ruleNumber = "4.4",
                    pageNumber = 24
                ),
                explanation = "Knee wraps are permitted only in the equipped division."
            ),
            QuizQuestion(
                id = 15,
                question = "How wide must the squat racks be set?",
                options = listOf(
                    "Exactly 1 meter",
                    "At the lifter's discretion within limits",
                    "Standard width for all lifters",
                    "Determined by the referee"
                ),
                correctAnswerIndex = 1,
                ruleReference = RuleReference(
                    section = "Equipment Specifications",
                    subsection = "Racks and Platforms",
                    ruleNumber = "1.4",
                    pageNumber = 12
                ),
                explanation = "The lifter may adjust the rack height and width according to their preference within available settings."
            )
        )
    }
}
