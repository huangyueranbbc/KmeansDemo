package com.hyr.kmeans;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * @date 2018-02-09 下午 1:52
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class KmeansMain {

    public static void main(String[] args) throws IOException {
        // 读取数据源文件
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data.csv")); // 数据源
        FileWriter writer = new FileWriter("src/main/resources/out.csv");
        List<String[]> myEntries = reader.readAll(); // 6.8, 12.6

        // 转换数据点集
        List<Point> points = new ArrayList<Point>(); // 数据点集
        for (String[] entry : myEntries) {
            points.add(new Point(Float.parseFloat(entry[0]), Float.parseFloat(entry[1])));
        }

        int k = 5; // K值
        KmeansModel model = Kmeans.run(points, k);

        writer.write("====================   K is "+model.getK() +"   Object Funcion Value is "+model.getOfv()+"   ====================\n");
        int i = 0;
        for (Cluster cluster : model.getClusters()) {
            i++;
            writer.write("====================   classification " + i + "   ====================\n");
            for (Point point: cluster.getPoints()) {
                writer.write(point.toString() + "\n");
            }
            writer.write("\n");
            writer.write("centroid is "+cluster.getCentroid().toString());
            writer.write("\n\n");
        }

        writer.close();

    }

}
