import com.csvreader.CsvReader;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Reaf {
    public static ArrayList<String[]> readCsvFile(String filePath) {
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        try {

//            ArrayList<Object> al = new ArrayList<>();
//            al.add(1);
//            al.add("a");
//            for (int i = 0; i < al.size(); i++){
//                System.out.println(al.get(i));
//            }
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
//          reader.readHeaders(); //跳过表头,不跳可以注释掉

            while (reader.readRecord()) {
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + csvList.size());
            int rsize = csvList.get(0).length;
//            System.out.println(rsize);

//            for(int row=1;row<csvList.size();row++){
//                for (int size = 0; size<rsize; size++) {
//                    //打印每一行的数据
//                    System.out.print(csvList.get(row)[size]);
//                    if (size!=rsize-1) {
//                        System.out.print(",");
//                    }
//                }
//                System.out.println();
//           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvList;
    }

    public static ArrayList<Node> alNode(ArrayList<String[]> als){
        ArrayList<Node> aln = new ArrayList<Node>();
        for (int i = 0; i < als.size(); i++){
                Node nd = new Node();
                nd.setNodeId(als.get(i)[0]);
                nd.setXcoord(6);
//                nd.setXcoord(Integer.valueOf(als.get(i)[1]).intValue());
//                nd.setYcoord(Integer.valueOf(als.get(i)[2]).intValue());
//                nd.setFloor(Integer.valueOf(als.get(i)[3]).intValue());
                nd.setBuilding(als.get(i)[4]);
                nd.setNodeType(als.get(i)[5]);
                nd.setLongName(als.get(i)[6]);
                nd.setShortName(als.get(i)[7]);
                nd.setTeamAssigned(als.get(i)[8]);
                aln.add(nd);
        }
        return aln;
    }
public static void printNs(ArrayList<Node> aln) throws IllegalAccessException {
        for (int i = 0; i < aln.size(); i++){
            Node nd = aln.get(i);
            Field[] fields = nd.getClass().getDeclaredFields();
            for (Field field:fields){
                System.out.print(field.get(nd)+",");
            }
            System.out.println();
        }
}
    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<String[]> c = readCsvFile("src/MapBnodes.csv");
//        for (int row = 1; row < c.size(); row++) {
//            for (int size = 0; size < c.get(0).length; size++) {
//                //打印每一行的数据
//                System.out.print(c.get(row)[size]);
//                if (size != c.size() - 1) {
//                    System.out.print(",");
//                }
//            }
//            System.out.println();
//        }
        printNs(alNode(c));
       // String a = c.get(0)[1];
   //     System.out.println(c.get(0)[0].getClass().getName());
    }
}
