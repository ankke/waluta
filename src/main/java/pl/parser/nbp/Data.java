package pl.parser.nbp;

import java.util.List;

/**
 * Class used for storing data: selling and buying rates.
 */
class Data {

    private List<Double> sellingRates;
    private List<Double> buyingRates;
    private int counter;

    List<Double> getSellingRates() {
        return sellingRates;
    }

    void setCounter(int counter) {
        this.counter = counter;
    }

    int getCounter() {
        return counter;
    }

    List<Double> getBuyingRates() {
        return buyingRates;
    }

    /**Adds rate to the selling rates list
     * @param rate rate value
     */
    void addToSellingRates(Double rate){
        sellingRates.add(rate);
    }

    /**Adds rate to the buying rates list
     * @param rate rate value
     */
    void addToBuyingRates(Double rate){
        buyingRates.add(rate);
    }

    Data(List<Double> sellingRates, List<Double> buyingRates, int counter){
        this.sellingRates = sellingRates;
        this.buyingRates = buyingRates;
        this.counter = counter;
    }

}
