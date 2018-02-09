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

    public KmeansModel(List<Cluster> clusters, Double ofv, int k) {
        this.clusters = clusters;
        this.ofv = ofv;
        this.k = k;
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

}
