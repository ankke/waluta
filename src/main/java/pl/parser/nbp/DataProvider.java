package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Class used to provide data stored in Data object. Thanks to specified Parser and FileProvider type, this class does not depend on type of used file.
 */
class DataProvider {

    /**Method used to fill Data object fields with proper values. Uses Parser for FileProvider, which are passed to the method.
     * If exception in Parser or FileProvider is thrown data.counter is decremented, because of lack of data from that day.
     * @param cur Enum currency
     * @param date date of file
     * @param parser proper parser
     * @param fp proper file
     * @param data Data instance to store extracted from file values
     */
    private static void collectRates(Currency cur, Date date, IParser parser, IFileProvider fp, Data data){
        try{
            String file = fp.getFile(date);
            data.addToSellingRates(parser.getValue(cur, file, "kurs_sprzedazy"));
            data.addToBuyingRates(parser.getValue(cur, file, "kurs_kupna"));
        }catch (Exception e){
            int tmp = data.getCounter();
            data.setCounter(--tmp);
        }
    }

    /**
     * @param cur Enum currency
     * @param start start date
     * @param end end date
     * @param fp FileProvider type
     * @param parser Parser type
     * @return filled Data instance
     * @throws ParseException when date format is incompatible with yyyy-MM-dd
     * @throws IllegalArgumentException when date is from future or date is invalid e.g. month number is 30
     */
    static Data collectData(Currency cur, String start, String end, IFileProvider fp, IParser parser) throws ParseException, IllegalArgumentException{

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        calStart.setTime(startDate);
        calEnd.setTime(endDate);

        List<Double> sellingRates = new LinkedList<>();
        List<Double> buyingRates = new LinkedList<>();
        int counter = 0;

        Data data = new Data(sellingRates, buyingRates, counter);

        if(calStart.after(current) || calEnd.after(current)) throw new IllegalArgumentException("Dates cannot be from the future.");

        while(calStart.before(calEnd) || calStart.equals(calEnd)){
            collectRates(cur, calStart.getTime(), parser, fp, data);
            calStart.add(Calendar.DATE, 1);
            int tmp = data.getCounter();
            data.setCounter(++tmp);
        }

        return data;

    }

}
