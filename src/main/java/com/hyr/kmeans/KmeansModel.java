package com.hyr.kmeans;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * @date 2018-02-09 下午 3:35
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class KmeansModel {

    private List<Cluster> clusters = new ArrayList<Cluster>();
    private Double ofv;
    private int k;  // k值
    private int calc_distance_type;

    public KmeansModel(List<Cluster> clusters, Double ofv, int k, int calc_distance_type) {
        this.clusters = clusters;
        this.ofv = ofv;
        this.k = k;
        this.calc_distance_type = calc_distance_type;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public Double getOfv() {
        return ofv;
    }

    public int getK() {
        return k;
    }

    public int getCalc_distance_type() {
        return calc_distance_type;
    }
}
