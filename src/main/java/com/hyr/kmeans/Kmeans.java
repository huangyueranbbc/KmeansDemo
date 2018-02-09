package com.hyr.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*******************************************************************************
 * @date 2018-02-09 下午 2:07
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class Kmeans {

    /**
     * kmeans
     *
     * @param points 数据集
     * @param k      K值
     * @param k      计算距离方式
     */
    public static KmeansModel run(List<Point> points, int k, int type) {
        // 初始化质心点
        List<Cluster> clusters = initCentroides(points, k);

        while (!checkConvergence(clusters)) {
            // 1.计算距离对每个点进行分类
            // 2.判断质心点是否改变,未改变则该分类已经收敛
            // 3.重新生成质心点
            initClusters(clusters); // 重置分类中的点
            classifyPoint(points, clusters, type);// 计算距离进行分类
            recalcularCentroides(clusters); // 重新计算质心点
        }

        // 计算目标函数值
        Double ofv = calcularObjetiFuncionValue(clusters);

        KmeansModel kmeansModel = new KmeansModel(clusters, ofv, k, type);

        return kmeansModel;
    }

    /**
     * 初始化k个质心点
     *
     * @param points 点集
     * @param k      K值
     * @return 分类集合对象
     */
    private static List<Cluster> initCentroides(List<Point> points, Integer k) {
        List<Cluster> centroides = new ArrayList<Cluster>();

        // 求出数据集的范围(找出所有点的x最小、最大和y最小、最大坐标。)
        Float max_X = Float.NEGATIVE_INFINITY;
        Float max_Y = Float.NEGATIVE_INFINITY;
        Float min_X = Float.POSITIVE_INFINITY;
        Float min_Y = Float.POSITIVE_INFINITY;
        for (Point point : points) {
            max_X = max_X < point.getX() ? point.getX() : max_X;
            max_Y = max_Y < point.getY() ? point.getY() : max_Y;
            min_X = min_X > point.getX() ? point.getX() : min_X;
            min_Y = min_Y > point.getY() ? point.getY() : min_Y;
        }
        System.out.println("min_X" + min_X + ",max_X:" + max_X + ",min_Y" + min_Y + ",max_Y" + max_Y);

        // 在范围内随机初始化k个质心点
        Random random = new Random();
        // 随机初始化k个中心点
        for (int i = 0; i < k; i++) {
            float x = random.nextFloat() * (max_X - min_X) + min_X;
            float y = random.nextFloat() * (max_Y - min_Y) + min_X;
            Cluster c = new Cluster();
            Point centroide = new Point(x, y); // 初始化的随机中心点
            c.setCentroid(centroide);
            centroides.add(c);
        }

        return centroides;
    }

    /**
     * 重新计算质心点
     *
     * @param clusters
     */
    private static void recalcularCentroides(List<Cluster> clusters) {
        for (Cluster c : clusters) {
            if (c.getPoints().isEmpty()) {
                c.setConvergence(true);
                continue;
            }

            // 求均值,作为新的质心点
            Float x;
            Float y;
            Float sum_x = 0f;
            Float sum_y = 0f;
            for (Point point : c.getPoints()) {
                sum_x += point.getX();
                sum_y += point.getY();
            }
            x = sum_x / c.getPoints().size();
            y = sum_y / c.getPoints().size();
            Point nuevoCentroide = new Point(x, y); // 新的质心点

            if (nuevoCentroide.equals(c.getCentroid())) { // 如果质心点不再改变 则该分类已经收敛
                c.setConvergence(true);
            } else {
                c.setCentroid(nuevoCentroide);
            }
        }
    }

    /**
     * 计算距离,对点集进行分类
     *
     * @param points   点集
     * @param clusters 分类
     * @param type     计算距离方式
     */
    private static void classifyPoint(List<Point> points, List<Cluster> clusters, int type) {
        for (Point point : points) {
            Cluster masCercano = clusters.get(0); // 该点计算距离后所属的分类
            Double minDistancia = Double.MAX_VALUE; // 最小距离
            for (Cluster cluster : clusters) {
                Double distancia = point.calculateDistance(cluster
                        .getCentroid(), type); // 点和每个分类质心点的距离
                if (minDistancia > distancia) { // 得到该点和k个质心点最先的距离
                    minDistancia = distancia;
                    masCercano = cluster; // 得到该点的分类
                }
            }
            masCercano.getPoints().add(point); // 将该点添加到距离最近的分类中
        }
    }

    private static void initClusters(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            cluster.initPoint();
        }
    }

    /**
     * 检查收敛
     *
     * @param clusters
     * @return
     */
    private static boolean checkConvergence(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            if (!cluster.isConvergence()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算目标函数值
     *
     * @param clusters
     * @return
     */
    private static Double calcularObjetiFuncionValue(List<Cluster> clusters) {
        Double ofv = 0d;

        for (Cluster cluster : clusters) {
            for (Point point : cluster.getPoints()) {
                int type = 1;
                ofv += point.calculateDistance(cluster.getCentroid(), type);
            }
        }

        return ofv;
    }
}
