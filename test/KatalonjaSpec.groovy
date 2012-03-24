import spock.lang.Specification

import org.egi.katalonja.SeafoodBusiness
/**
 * Created by IntelliJ IDEA.
 * User: ruben
 * Date: 23/03/12
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
class KatalonjaSpec extends Specification{


    def merchandise
    def city
    SeafoodBusiness seafoodBusiness

    def setup(){
        seafoodBusiness = new SeafoodBusiness()
    }

    def "should return 'Madrid' when seafood sell price is much more higher"(){

        given: merchandise = ["scallop":10 , "octopus":10, "spider crap":10]

        and: seafoodBusiness.sellPrices = ["Madrid" : ["scallop":100 , "octopus":100, "spider crap":100] ,
                                           "Lisboa" : ["scallop":1 , "octopus":1, "spider crap":1] ,
                                           "Barcelona" : ["scallop":1 , "octopus":1, "spider crap":1] ]

        when: city = seafoodBusiness.maxProfitCity(merchandise)

        then: city == "Madrid"

    }

    def "should return 'Lisboa' when seafood sell price is much more higher"(){

        given: merchandise = ["scallop":10 , "octopus":10, "spider crap":10]

        and: seafoodBusiness.sellPrices = ["Lisboa" : ["scallop":100 , "octopus":100, "spider crap":100] ,
                                           "Madrid" : ["scallop":1 , "octopus":1, "spider crap":1] ,
                                           "Barcelona" : ["scallop":1 , "octopus":1, "spider crap":1] ]

        when: city = seafoodBusiness.maxProfitCity(merchandise)

        then: city == "Lisboa"

    }

    def "should return 'Madrid' when has the same sell price and it's closest city"(){

        given: merchandise = ["scallop":10 , "octopus":10, "spider crap":10]


        and: seafoodBusiness.distances = ["Madrid":100,"Lisboa":800,"Barcelona":1100]

        and: seafoodBusiness.sellPrices = ["Lisboa" : ["scallop":100 , "octopus":100, "spider crap":100] ,
                                           "Madrid" : ["scallop":100 , "octopus":100, "spider crap":100] ,
                                           "Barcelona" : ["scallop":100 , "octopus":100, "spider crap":100] ]



        when: city = seafoodBusiness.maxProfitCity(merchandise)

        then: city == "Madrid"

    }

    def "should return 'Lisboa' when has the less sell price and it's closest city"(){

        given: merchandise = ["scallop":10 , "octopus":10, "spider crap":10]

        and: seafoodBusiness.distances = ["Madrid":1000,"Lisboa":900,"Barcelona":1000]

        and: seafoodBusiness.sellPrices = ["Lisboa" : ["scallop":93, "octopus":93, "spider crap":93] ,
                "Madrid" : ["scallop":100 , "octopus":100, "spider crap":100] ,
                "Barcelona" : ["scallop":100 , "octopus":100, "spider crap":100] ]


        when: city = seafoodBusiness.maxProfitCity(merchandise)

        then: city == "Lisboa"

    }

    def "should return 'Lisboa' when resolve kata lonja problem"(){

        given: merchandise = ["scallop":50 , "octopus":100, "spider crap":50]

        and: seafoodBusiness.sellPrices = ["Lisboa" : ["scallop":600, "octopus":100, "spider crap":500] ,
                "Madrid" : ["scallop":500 , "octopus":0, "spider crap":450] ,
                "Barcelona" : ["scallop":450 , "octopus":120, "spider crap":0] ]

        when: city = seafoodBusiness.maxProfitCity(merchandise)

        then: city == "Lisboa"

    }
}
