/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class11;

import Class9.Stats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lkhan
 */
public class Class11B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("C:\\Users\\lkhan\\Downloads\\MSFT.csv");
        //Example of checked exception handling
        try {
            //Step1
            FileReader filereader = new FileReader(file);
            BufferedReader buffread = new BufferedReader(filereader);
            buffread.readLine();//header and we are reading off so we don't have to deal with it.
            String line = "";

            ArrayList<DailyPrices> msft = new ArrayList();
            DailyPrices dailyPrices;
            while ((line = buffread.readLine()) != null) {
                String lineArray[] = line.trim().split(",");
                dailyPrices = new DailyPrices(lineArray);
                msft.add(dailyPrices);
            }
            //Step3 A for lop to do analytics                        
            int lookBack = 7;
            float shortMemory[] = new float[lookBack];
            
            for (int i = 0; i < msft.size(); i++) {
                if (i > (lookBack - 1)) {
                    for (int k = 0; k < shortMemory.length; k++) {
                        shortMemory[k] = msft.get(i - k - 1).getClose();
                    }
                    System.out.printf("Date:%s,Open:%f,High:%f,Low:%f,Close:%f,"
                            + "AdjClose:%f,Volume:%,d,Dollar Volume:%,f,MA7:%f\n",
                            msft.get(i).getDate(), msft.get(i).getOpen(), msft.get(i).getHigh(),
                            msft.get(i).getLow(), msft.get(i).getClose(), msft.get(i).getAdjClose(),
                            msft.get(i).getVolume(), msft.get(i).getDollarVolume(),
                            Stats.averageOfElements(shortMemory));
                }

            }
        } catch (IOException io) {
            io.printStackTrace();
//            System.out.println(io.getLocalizedMessage());
        } finally {
            System.out.println("We read the file successfully");
        }
    }

}
