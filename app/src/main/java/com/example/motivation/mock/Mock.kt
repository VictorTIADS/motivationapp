package com.example.motivation.mock

import android.util.Log
import com.example.motivation.util.MotivationConstants
import java.util.*


class Frase(val description: String,val category: Int)







class Mock {

    private val ALL = MotivationConstants.FRASE_FILTER.ALL
    private val SUN = MotivationConstants.FRASE_FILTER.SUN
    private val HAPPY = MotivationConstants.FRASE_FILTER.HAAPY

    private val mListFrases: List<Frase> = listOf(
        Frase("O não você ja tem.Agora falta buscar a humilhação!", HAPPY),
        Frase("Procure saber antes se é impossível, porque daí você nem tenta!", HAPPY),
        Frase("Não deixe para desistir amanhã do que você pode desistir hoje!", HAPPY),
        Frase("A morte é inevitável!", HAPPY),
        Frase("Vai dar tudo certo, menos pra você!", HAPPY),
        Frase("O caminho é longo mas a derrota é certa!", HAPPY),
        Frase("Desista!", HAPPY),
        Frase("Não importa as escolhas que você faça, estará ferrado do mesmo jeito!", HAPPY),
        Frase("Pare de tentar, e comece a desistir!", HAPPY),
        Frase("Desistir é para os fracos, ideal é nem tentar!", HAPPY),
        Frase("O pior está por vir, acredite!",HAPPY),
        Frase("Nunca se esqueça que você é limitado!",HAPPY),
        Frase("Lute como nunca, perca como sempre!",HAPPY),
        Frase("Uma mente positiva e otimista tem sempre uma jornada linda, repleta de cores novas e alegres.",SUN),
        Frase("Não procure sucesso nem riqueza, mas sim pessoas lindas em jornadas especiais como a de hoje.",SUN),
        Frase("Desafie seus limites, vá além dos seus horizontes e passe a saborear a vida de uma forma mais plena. ",SUN),
        Frase("Pensamento positivo e esperança no coração é tudo que preciso para ser feliz.",SUN),
        Frase("Não importa o que acontece ao seu redor, pois só depende de você a beleza do seu dia!",SUN),
        Frase("Todas as manhãs você acorda com a oportunidade de fazer da sua vida o que bem entender.",SUN),
        Frase("Não há motivo certo para aproveitar um novo dia. Com chuva ou sol o que é preciso é ser feliz!",SUN),
        Frase("A beleza de um dia depende sempre e só de quem o vê e da forma como o enxerga. Tenha um dia lindo do seu jeito!",SUN),
        Frase("As oportunidades estão à sua espera, vá e agarre-as!",SUN),
        Frase("Que se alegrem os rostos e se preparem os corações para instantes de alegria.",SUN),
        Frase("O amanhecer é mais belo para os que amam, por isso hoje tudo vai ser maravilhoso.",SUN),
        Frase("Aproveite cada dia para viver a vida que você escolheu e nunca a vida que os outros querem que você viva.",SUN),
        Frase("Nada na vida fará você ser feliz até que você escolha ser feliz.",SUN)

    )


    fun getFrase(value:Int):String{

        var filtred = mListFrases.filter {it->  (it.category == value || value==ALL) }

        var rand = Random().nextInt(filtred.size)

        return filtred[rand].description


    }
}