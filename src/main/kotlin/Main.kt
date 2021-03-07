var amount: Int = 0
var countSongs: Int = 0

var status: Boolean = false
var countOfStatus: Int = 0
fun main() {

    val payZero: Int = 0
    val payOne: Int = 1
    val payTen: Int = 10
    val payOneHundred: Int = 100
    println("Добрый день, в нашем приложении появилась возможность покупать музыку. ")
    while (true) {
        println(
            """
            Введите следующее действие:
            1. Посмотреть баланс и статус;
            2. Посмотреть условия покупки музыки онлайн;
            3. Оплатить одну песню;
            4. оплатить десять песен;
            5. Оплатить сто песен;
            6. Пропустить оплату;
            7. Завершить работу с приложением.
        """.trimIndent()
        )
        try {
            var chose: Int = readLine()?.toInt() ?: 0
            when (chose) {
                1 -> {
                    println("Вы купили песен на сумму ${amount.toDouble() / 100} руб. (или $amount копеек). Ваш стутус ${checkStatus()}, " +
                            "всего Вы приобрели $countSongs песен. Стоимость следующей песни составить ${countDiscount().toDouble() / 100} руб.")

                }
                2 -> println(
                    """
                    Условия пользования музыкальным сервисом следующие:

                    Если предыдущая сумма покупок до 1 000 р. - скидки отсутствуют;
                    Если предыдущая сумма покупок от 1 001 до 10 000 р. - каждая 11 песня бесплатная;
                    Если предыдущая сумма покупок от 10 001 р. и выше - скидка на каждую композицию составляет 5% от суммы;
                    
                    Дополнительно:
                    При этом постоянные пользователи приобретают статус "меломаны", дополнительно получают 1% скидки на следующие покупки.
                """.trimIndent()
                )
                3 -> {
                    pay(payOne)
                }
                4 -> {
                    pay(payTen)
                }
                5 -> {
                    pay(payOneHundred)
                }
                6 -> {
                    pay(payZero)
                }
                7 -> break
                else -> println("Вы ввели число, которого нет в меню. \n")
            }
        } catch (e: Exception) {
            println("Необходимо выбрать цифру")
        } finally {
            println("----------------------------------------------------------------------------------------------------")
        }

    }
    println(" Спасибо, что воспользовались нашим сервисом")
}


fun pay(paySong: Int) {

    var i = 0
    if(paySong == 0){
        countOfStatus = 0
        changeStatus()
    }
    while (paySong > i){
        i++
        countOfStatus++
        changeStatus()
        countSongs++
        var priceOfNextSong = countDiscount()
        amount+= priceOfNextSong
        println("Вы купили новую песню. стоимость песни составляет ${priceOfNextSong.toDouble() / 100} руб. (или $priceOfNextSong копеек)")
    }

}

fun countDiscount(): Int {
    var itemPrice: Int = 100_00
    val itemCount: Int = 11
    val discountFirst: Int = 1_000_00
    val discountSecond: Int = 10_000_00


    if ((amount > discountFirst) && (countSongs % itemCount == 0)) {
        itemPrice = 0
    }

    if (amount > discountSecond) {
        itemPrice = itemPrice * 95 / 100
    }
    if (status) {
        itemPrice = itemPrice * 99 / 100
    }
    return itemPrice
}

fun changeStatus(){
    if(countOfStatus > 3 && !status){
        status = true
        println("Вы приобрели три песни подряд и не пропустили оплату. Вам присвоен статус ${checkStatus()}")
    }
    if(countOfStatus < 3 && status){
        status = false
        println("Вы пропустили платеж, ваш статус стал ${checkStatus()}")
    }
}
fun checkStatus(): String {
    val meloman: String = "меломан"
    val notMeloman: String = "обычный пользователь"
    if (status) {
        return meloman
    } else {
        return notMeloman
    }
}