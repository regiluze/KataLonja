package org.egi.katalonja

/**
 * Created by IntelliJ IDEA.
 * User: ruben
 * Date: 24/03/12
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
class SeafoodBusiness {

    static def KM_PENALTY = 2

    Map<String,Integer> distances = ["Madrid":800,"Lisboa":600,"Barcelona":1100]
    Map<String, Map<String, Integer>> sellPrices

    def maxProfitCity(merchandise) {
        Map profit = calculateAllSale(merchandise)
        kilometerPenalty(profit)
        return profit.max { it.value }.key
    }

    private kilometerPenalty(Map profit) {
        distances.each { city, km ->
            profit[city] -= km * KM_PENALTY
        }
    }

    private calculateAllSale(merchandise) {
        Map profit = [:]
        sellPrices.each { key, prices ->
            def sellMount = 0
            merchandise.each { seafood, amount ->
                sellMount += amount * prices[seafood]
            }
            profit.put(key, sellMount)
        }
        return profit
    }

    public setSellPrices(sellPrices){
        this.sellPrices = depreciatePricesByDistance(sellPrices)
    }

    Map<String, Map<String, Integer>> depreciatePricesByDistance(sellPrices) {

        def newPricesByCities = [:]
        sellPrices.each { city,prices ->
              def newPrices = [:]
              prices.each { seafood,price ->
                  double newPrice = price
                  (distances[city]/100).downto(1){
                      def devalutation = newPrice/100
                      newPrice -= devalutation
                  }
                  newPrices.put(seafood,newPrice)
              }
            newPricesByCities.put(city,newPrices)
        }
        return newPricesByCities
    }

}
