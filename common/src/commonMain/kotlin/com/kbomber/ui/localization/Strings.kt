package com.kbomber.ui.localization

import androidx.compose.runtime.staticCompositionLocalOf

sealed interface Strings {
    val startAttack: String
    val endAttack: String
    val phoneNumber: String
    val phoneNumberIsInvalid: String
    val invalidCycleCount: String
    val countOfCycles: String

    val ukraine: String
    val russia: String
    val belarus: String
    val kazakhstan: String

    val description: String

    fun requestsExecuted(count: Int): String

    val weOnGitHub: String
    val weInTelegram: String

    object Ukrainian : Strings {
        override val startAttack: String = "Розпочати атаку"
        override val endAttack: String = "Закінчити атаку"
        override val phoneNumber: String = "Номер телефону"
        override val phoneNumberIsInvalid: String = "Неправильний формат номеру телефона"
        override val invalidCycleCount: String = "Некоректно введена кількість циклів"
        override val countOfCycles: String = "Кількість циклів"
        override val ukraine: String = "Україна"
        override val russia: String = "росія"
        override val belarus: String = "Білорусь"
        override val kazakhstan: String = "Казахстан"

        override val description: String = "" +
            "SMS-бомбер - це програма, яка автоматично відправляє багато повідомлень на один номер телефону.\n" +
            "\n" +
            "Для створення SMS-бомбера необхідно:\n" +
            "\n" +
            "1. Вказати номер телефону, на який будуть відправлятися повідомлення.\n" +
            "2. Вказати кількість циклів (скільки разів буде повторюватися відправка повідомлення).\n" +
            "3. Запустити програму та дочекатися завершення відправки всіх повідомлень.\n\n" +
            "Враховуйте те, що кожен сервіс може мати свої обмеження."

        override fun requestsExecuted(count: Int): String {
            return "$count запитів виконано."
        }

        override val weOnGitHub: String = "Ми на GitHub"
        override val weInTelegram: String = "Ми в Telegram"
    }

    object Russian : Strings {
        override val startAttack: String = "Начать атаку"
        override val endAttack: String = "Закончить атаку"
        override val phoneNumber: String = "Номер телефона"
        override val phoneNumberIsInvalid: String = "Неверный формат номера телефона"
        override val invalidCycleCount: String = "Некорректно введенное количество циклов"
        override val countOfCycles: String = "Количество циклов"
        override val ukraine: String = "Украина"
        override val russia: String = "Россия"
        override val belarus: String = "Беларусь"
        override val kazakhstan: String = "Казахстан"

        override fun requestsExecuted(count: Int): String {
            return "$count запросов выполнено."
        }

        override val weOnGitHub: String = "Мы на GitHub"
        override val weInTelegram: String = "Мы в Telegram"

        override val description: String = "SMS-бомбер - это программа, которая автоматически отправляет множество сообщений на один номер телефона.\n" +
            "\n" +
            "Для создания SMS-бомбера необходимо:\n" +
            "\n" +
            "1. Указать номер телефона, на который будут отправляться сообщения.\n" +
            "2. Указать количество циклов (сколько раз будет повторяться отправка сообщения).\n" +
            "3. Запустить программу и дождаться окончания отправки всех сообщений.\n\n" +
            "Учитывайте, что каждый сервис имеет свои ограничения и не все СМС могут прийти."
    }
}

val LocalStrings = staticCompositionLocalOf<Strings> { error("Unspecified") }